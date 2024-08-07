package com.cimba.lazurite.wishlist;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.Wishlist;
import com.cimba.lazurite.file.FileStorageService;
import com.cimba.lazurite.repository.UserRepository;
import com.cimba.lazurite.repository.WishlistRepository;
import com.cimba.lazurite.entity.common.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.cimba.lazurite.wishlist.WishlistSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;
    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;
    private final MemberMapping memberMapper;

    public Long save(WishlistRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Wishlist wishlist = wishlistMapper.toWishlist(request);
        wishlist.setOwner(user);
        return wishlistRepository.save(wishlist).getId();
    }

    public WishlistResponse findById(Long id) {
        return wishlistRepository.findById(id)
                .map(wishlistMapper::toWishlistResponse)
                .orElseThrow(() -> new EntityNotFoundException("No wishlist found with the ID: " + id));
    }

    public PageResponse<WishlistResponse> findAllWishlists(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Wishlist> wishlists = wishlistRepository.findAllDisplayableWishlists(pageable, user.getIdUser());
        List<WishlistResponse> wishlistResponse = wishlists.stream()
                .map(wishlistMapper::toWishlistResponse)
                .toList();
        return new PageResponse<>(
                wishlistResponse,
                wishlists.getNumber(),
                wishlists.getSize(),
                wishlists.getTotalElements(),
                wishlists.getTotalPages(),
                wishlists.isFirst(),
                wishlists.isLast()
        );
    }

    public PageResponse<WishlistResponse> findAllWishlistsByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Wishlist> wishlists = wishlistRepository.findAll(withOwnerId(user.getIdUser()), pageable);
        List<WishlistResponse> wishlistResponse = wishlists.stream()
                .map(wishlistMapper::toWishlistResponse)
                .toList();
        return new PageResponse<>(
                wishlistResponse,
                wishlists.getNumber(),
                wishlists.getSize(),
                wishlists.getTotalElements(),
                wishlists.getTotalPages(),
                wishlists.isFirst(),
                wishlists.isLast()
        );
    }

    public void uploadWishlistCoverPicture(MultipartFile file, Authentication connectedUser, Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("No wishlist found with the ID: " + wishlistId));
        User user = ((User) connectedUser.getPrincipal());
        var wishlistCover = fileStorageService.saveFile(file, user.getIdUser());
        wishlist.setImage(wishlistCover);
        wishlistRepository.save(wishlist);
    }

    public WishlistResponse updateWishlist(Long wishlistId, WishlistRequest request, Authentication connectedUser) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("No wishlist found with the ID: " + wishlistId));
        User user = ((User) connectedUser.getPrincipal());
        if (!wishlist.getOwner().getIdUser().equals(user.getIdUser())) {
            throw new AccessDeniedException("You are not the owner of this wishlist");
        }

        if (request.name() != null) {
            wishlist.setName(request.name());
        }
        if (request.description() != null) {
            wishlist.setDescription(request.description());
        }
        return null;
    }

    public void deleteWishlist(Long id, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No wishlist found with the ID: " + id));

        if (!wishlist.getOwner().getIdUser().equals(user.getIdUser())) {
            throw new AccessDeniedException("You are not the owner of this wishlist");
        }

        wishlistRepository.delete(wishlist);

    }

    public void addMemberToWishlist(Long wishlistId, Long userId, Authentication connectedUser) {
        User owner = (User) connectedUser.getPrincipal();

        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() ->
                        new EntityNotFoundException("No wishlist found with the ID: " + wishlistId));

        if (!wishlist.getOwner().getIdUser().equals(owner.getIdUser())) {
            throw new AccessDeniedException("You are not the owner of this wishlist");
        }

        User member = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("No user found with the ID: " + userId));

        // Проверка, что пользователь еще не является участником
        if (wishlist.getMembers().contains(member)) {
            throw new IllegalStateException("User is already a member of this wishlist");
        }

        wishlist.getMembers().add(member);
        wishlistRepository.save(wishlist);
    }

    public void removeMemberFromWishlist(Long wishlistId, Long userId, Authentication connectedUser) {
        User owner = (User) connectedUser.getPrincipal();

        // Проверка наличия списка
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() ->
                        new EntityNotFoundException("No wishlist found with the ID: " + wishlistId));

        if (!wishlist.getOwner().getIdUser().equals(owner.getIdUser())) {
            throw new AccessDeniedException("You are not the owner of this wishlist");
        }

        User member = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("No user found with the ID: " + userId));

        if (!wishlist.getMembers().contains(member)) {
            throw new IllegalStateException("User is not a member of this wishlist");
        }

        wishlist.getMembers().remove(member);
        wishlistRepository.save(wishlist);
    }

    public PageResponse<MemberResponse> findWishlistMembers(int page, int size, Long wishlistId, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<User> members = userRepository.findMembersByWishlistId(wishlistId, pageable);
        List<MemberResponse> memberResponses = members.stream()
                .map(memberMapper::toMemberResponse)
                .toList();
        return new PageResponse<>(
                memberResponses,
                members.getNumber(),
                members.getSize(),
                members.getTotalElements(),
                members.getTotalPages(),
                members.isFirst(),
                members.isLast()
        );
    }
}
