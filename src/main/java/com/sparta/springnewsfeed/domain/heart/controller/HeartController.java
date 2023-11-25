package com.sparta.springnewsfeed.domain.heart;

import com.sparta.springnewsfeed.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/{postId}/hearts")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public void insert(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {
        heartService.insert(userDetails.getUser().getId(), postId);
    }

    @DeleteMapping("/{heartId}")
    public void delete(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId,
        @PathVariable Long heartId) {
        heartService.delete(userDetails.getUser().getId(), postId, heartId);
    }


}
