package mx.ojt.pxm.cameleootask.repo;

import mx.ojt.pxm.cameleootask.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepo extends JpaRepository<Quote,Long> {

    Long deleteQuoteById(Long id);
    List<Quote> findTop10ByOrderByRankDesc();
    List<Quote> findTop10ByOrderByRankAsc();
    @Query("SELECT q.id FROM Quote q")
    List<Long> getAllIds();

}
