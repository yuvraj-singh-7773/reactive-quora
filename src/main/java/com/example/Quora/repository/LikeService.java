package com.example.Quora.repository;

import com.example.Quora.dto.LikeRequestDTO;
import com.example.Quora.dto.LikeResponseDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LikeService {
    public Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);

    public Mono<LikeResponseDTO> getLikeByTargetIdAndTargetType(String TargetId,String TargetType);

    public Mono<LikeResponseDTO> DisLikeByTargetIdAndTargetType(String TargetId,String TargetType);

    public Mono<LikeResponseDTO> toggleLike(String TargetId,String TargetType,Boolean isLike);

}
