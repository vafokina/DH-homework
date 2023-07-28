package com.dh.homework.bid.web.rest;

import com.dh.homework.bid.dto.BidDto;
import com.dh.homework.bid.dto.CreateBidDto;
import com.dh.homework.bid.dto.UpdateBidDto;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @GetMapping
    public List<BidDto> getBids() {
        // todo
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public BidDto getBid(@PathVariable Long id) {
        // todo
        return null;
    }

    @PostMapping
    public BidDto createBid(@Valid @RequestBody CreateBidDto dto) {
        // todo
        return null;
    }

    @PutMapping("/{id}")
    public BidDto updateBid(@PathVariable Long id, @Valid @RequestBody UpdateBidDto dto) {
        // todo
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBid(@PathVariable Long id) {
        // todo
    }
}
