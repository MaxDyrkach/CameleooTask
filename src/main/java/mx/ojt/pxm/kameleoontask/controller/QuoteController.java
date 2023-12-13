package mx.ojt.pxm.kameleoontask.controller;

import jakarta.validation.Valid;
import mx.ojt.pxm.kameleoontask.model.dto.QuoteDto;
import mx.ojt.pxm.kameleoontask.model.dto.QuoteProgressDto;
import mx.ojt.pxm.kameleoontask.model.dto.VotingDto;
import mx.ojt.pxm.kameleoontask.service.QuoteService;
import mx.ojt.pxm.kameleoontask.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private VoteService voteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuotById(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuote(id));
    }

    @GetMapping("/get/rand")
    public ResponseEntity<?> getQuotByRand() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @GetMapping("/get/top/{dir}")
    public ResponseEntity<?> getTopQuot(@PathVariable String dir) {
        QuoteService.Direction d = QuoteService.Direction.valueOf(dir.toUpperCase());
        return ResponseEntity.ok(quoteService.getTopTenQuoteByVotes(d));
    }

    @GetMapping("/get/progress")
    public ResponseEntity<?> getQuotProgress(@RequestBody QuoteProgressDto qProgress) {
        return ResponseEntity.ok( voteService.getHistoryVotesByQuoteId(qProgress.getQuoteId(), qProgress.getStart().toInstant(),qProgress.getEnd().toInstant()));
    }



    @PostMapping("/new")
    public ResponseEntity<?> newQuote(@RequestBody QuoteDto qDto) throws Exception {
        return ResponseEntity.ok(quoteService.createQuote(qDto));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateQuote(@RequestBody QuoteDto qDto) {
        return ResponseEntity.ok(quoteService.updateQuoteContent(qDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteQuote(@RequestBody QuoteDto qDto) {
        return ResponseEntity.ok(quoteService.deleteQuote(qDto));
    }

    @PostMapping("/vote/{id}")
    public ResponseEntity<?> voteOnQuote(@PathVariable Long id, @Valid @RequestBody VotingDto vDto) {
        QuoteService.Direction d = QuoteService.Direction.valueOf(vDto.getVote().toUpperCase());
        return ResponseEntity.ok(quoteService.vote(id, d));
    }


}
