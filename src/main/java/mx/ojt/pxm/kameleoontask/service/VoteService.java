package mx.ojt.pxm.kameleoontask.service;

import mx.ojt.pxm.kameleoontask.model.Quote;
import mx.ojt.pxm.kameleoontask.model.Vote;
import mx.ojt.pxm.kameleoontask.repo.QuoteRepo;
import mx.ojt.pxm.kameleoontask.repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepo voteRepo;
    @Autowired
    private QuoteRepo quoteRepo;

    @Transactional
    public List<Vote> getHistoryVotesByQuoteId(Long qId, Instant start, Instant end) {
        Quote q = quoteRepo.findById(qId).orElseThrow();
        return voteRepo.findAllByQuoteAndTimeBetweenOrderByTime(q,start,end);
    }
}
