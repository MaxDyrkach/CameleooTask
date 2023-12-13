package mx.ojt.pxm.kameleoontask.repo;

import mx.ojt.pxm.kameleoontask.model.Quote;
import mx.ojt.pxm.kameleoontask.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface VoteRepo extends JpaRepository<Vote,Long> {

    Optional<Vote> findTopByQuoteOrderByTimeDesc(Quote q);
    List<Vote> findAllByQuoteAndTimeBetweenOrderByTime(Quote q, Instant st, Instant end);

}
