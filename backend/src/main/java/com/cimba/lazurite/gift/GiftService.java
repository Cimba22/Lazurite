package com.cimba.lazurite.gift;

import com.cimba.lazurite.entity.Gift;
import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.common.PageResponse;
import com.cimba.lazurite.repository.GiftRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cimba.lazurite.gift.GiftSpecification.withOwnerId;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;


    public Long save(GiftRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Gift gift = giftMapper.toGift(request);
        gift.setOwner(user);
        return giftRepository.save(gift).getId();
    }

    public GiftResponse findById(Long id) {
        return giftRepository.findById(id)
                .map(giftMapper::toGiftResponse)
                .orElseThrow(() -> new EntityNotFoundException("No gift found with the ID: " + id));
    }

    public PageResponse<GiftResponse> findAllGifts(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Gift> gifts = giftRepository.findAllDisplayableGifts(pageable, user.getIdUser());
        List<GiftResponse> giftResponse = gifts.stream()
                .map(giftMapper::toGiftResponse)
                .toList();
        return new PageResponse<>(
                giftResponse,
                gifts.getNumber(),
                gifts.getSize(),
                gifts.getTotalElements(),
                gifts.getTotalPages(),
                gifts.isFirst(),
                gifts.isLast()
        );
    }


    public PageResponse<GiftResponse> findAllGiftsByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Gift> gifts = giftRepository.findAll(withOwnerId(user.getIdUser()), pageable);
        List<GiftResponse> giftResponse = gifts.stream()
                .map(giftMapper::toGiftResponse)
                .toList();
        return new PageResponse<>(
                giftResponse,
                gifts.getNumber(),
                gifts.getSize(),
                gifts.getTotalElements(),
                gifts.getTotalPages(),
                gifts.isFirst(),
                gifts.isLast()
        );
    }

    public PageResponse<GiftResponse> findAllArchivedGifts(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        //TODO пересмотреть связи, возможно добавить таблицу с историей
        return null;
    }
}
