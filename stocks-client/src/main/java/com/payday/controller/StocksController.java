package com.payday.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payday.dto.BaseTransactionDto;
import com.payday.dto.GenericCommentDto;
import com.payday.dto.SpecialCommentDto;
import com.payday.dto.TransactionDto;
import com.payday.dto.UserStocksDto;
import com.payday.service.impl.UserStocksServiceImpl;


@Controller
public class StocksController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private UserStocksServiceImpl userStocksService;
	
    @GetMapping("/")
    public String getAllStocks() {
        return "all-stocks";
    }
    
    
    @GetMapping("/sample-notification/{message}")
    @ResponseBody
    public String sendNotification(@PathVariable String message) {
    	GenericCommentDto genericComment = new GenericCommentDto("none", message, LocalDateTime.now());
    	simpMessagingTemplate.convertAndSendToUser("user1", "/topic/comments", genericComment);
        return message;
    }
    
    @PostMapping("/transaction")
    @ResponseBody
    public TransactionDto transaction(@RequestBody BaseTransactionDto baseTransactionDto) {
    	TransactionDto dto = userStocksService.stockTransaction(baseTransactionDto);
        return dto;
    }
    
    @GetMapping("/my-stocks")
    public String getMyStocks(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String userId = authentication.getName();
    	List<UserStocksDto> userStocks = userStocksService.getUserStocks(userId);
    	model.addAttribute("stocks", userStocks);
        return "my-stocks";
    }
    
    @MessageMapping("/app/comment")
    public void addComment(@Payload SpecialCommentDto comment) {
        GenericCommentDto generixComment = new GenericCommentDto(comment.getFromUser(), comment.getMessage(), LocalDateTime.now());
        if (comment.getToUser().isEmpty()) {
            simpMessagingTemplate.convertAndSend("/topic/comments", generixComment);
        } else {
            simpMessagingTemplate.convertAndSendToUser(comment.getToUser(), "/topic/comments", generixComment);
        }
    }

}
