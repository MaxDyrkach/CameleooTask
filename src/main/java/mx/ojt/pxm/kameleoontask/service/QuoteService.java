package mx.ojt.pxm.kameleoontask.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.ojt.pxm.kameleoontask.model.Quote;
import mx.ojt.pxm.kameleoontask.model.User;
import mx.ojt.pxm.kameleoontask.model.Vote;
import mx.ojt.pxm.kameleoontask.model.dto.QuoteDto;
import mx.ojt.pxm.kameleoontask.repo.QuoteRepo;
import mx.ojt.pxm.kameleoontask.repo.UserRepo;
import mx.ojt.pxm.kameleoontask.repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class QuoteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QuoteRepo quoteRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VoteRepo voteRepo;

    @Transactional(readOnly = true)
    public Optional<Quote> getQuote(Long qId) {
        return quoteRepo.findById(qId);
    }

    @Transactional
    public Optional<Quote> createQuote(QuoteDto qDto) throws Exception {
        User principal = userRepo.findUserByName(qDto.getUserPosted()).orElseThrow();
        Quote q = Quote.builder().content(qDto.getContent()).userPosted(principal).dateCreation(Instant.now())
                .dateUpdate(ZonedDateTime.now().toInstant()).votes(new HashSet<>()).rank(0L).build();
        return Optional.ofNullable(quoteRepo.save(q));
    }

    @Transactional
    public Optional<Quote> updateQuoteContent(QuoteDto qDto) {
        Quote q = quoteRepo.findById(qDto.getId()).orElseThrow();
        q.setContent(qDto.getContent());
        q.setDateUpdate(ZonedDateTime.now().toInstant());
        return Optional.ofNullable(quoteRepo.save(q));
    }

    @Transactional
    public Long deleteQuote(QuoteDto qDto) {
        Quote q = quoteRepo.findById(qDto.getId()).orElseThrow();
        Long r = quoteRepo.deleteQuoteById(qDto.getId());

        return r;

    }

    @Transactional
    public Long vote(Long qId, Direction dir) {
        Quote q = quoteRepo.findById(qId).orElseThrow();
        ZonedDateTime time = ZonedDateTime.now();
        time = time.truncatedTo(ChronoUnit.MINUTES).minusMinutes(time.getMinute() % 15);
        Optional<Vote> voteOpt = voteRepo.findTopByQuoteOrderByTimeDesc(q);
        Vote v = null;
        if (voteOpt.isEmpty() || voteOpt.get().getTime().isBefore(time.toInstant())) {
            v = Vote.builder().quote(q).time(time.toInstant()).val(0L).build();
        } else {
            v = voteOpt.get();
        }
        switch (dir) {
            case UP -> {
                v.setVal(v.getVal() + 1);
                q.setRank(q.getRank()+1);
            }
            case DOWN -> {
                v.setVal(v.getVal() - 1);
                q.setRank(q.getRank()-1);
            }
        }
        voteRepo.save(v);
        return v.getVal();

    }

    @Transactional(readOnly = true)
    public List<Quote> getTopTenQuoteByVotes(Direction dir) {
        return switch (dir) {
            case UP -> quoteRepo.findTop10ByOrderByRankDesc();
            case DOWN -> quoteRepo.findTop10ByOrderByRankAsc();
        };
    }

    @Transactional(readOnly = true)
    public Optional<Quote> getRandomQuote() {
        List<Long> qIds = quoteRepo.getAllIds();
        if (!qIds.isEmpty()) return quoteRepo.findById(qIds.get(new Random().nextInt(qIds.size() - 1)));
        return Optional.empty();

    }


    public enum Direction {UP, DOWN}
}
