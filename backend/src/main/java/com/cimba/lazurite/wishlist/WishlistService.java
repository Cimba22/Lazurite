package com.cimba.lazurite.wishlist;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.Wishlist;
import com.cimba.lazurite.file.FileStorageService;
import com.cimba.lazurite.repository.WishlistRepository;
import com.cimba.lazurite.entity.common.PageResponse;
import jakarta.mail.internet.MimeBodyPart;
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
    private FileStorageService fileStorageService;

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
}
