package com.example.poccachespring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/system")
public class SystemController {
    private static final LocalDateTime startedTime = LocalDateTime.now();


    @GetMapping("/check")
    public ResponseEntity<JsonNode> check() {
        ObjectNode systemStatus = JsonNodeFactory.instance.objectNode()
                .put("status", "server's up! :)")
                .put("now", LocalDateTime.now().toString())
                .put("started_time", startedTime.toString());

        return ResponseEntity.ok(systemStatus);
    }

}
