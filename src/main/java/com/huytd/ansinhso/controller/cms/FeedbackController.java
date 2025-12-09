package com.huytd.ansinhso.controller.cms;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("feedbackController")
@RequestMapping("/app-api/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback Management", description = "APIs for managing feedback")
public class FeedbackController {
}
