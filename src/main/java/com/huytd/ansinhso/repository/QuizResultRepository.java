package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.QuizResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, String> {
//    List<QuizResult> findByUserIdOrderByCompletedAtDesc(String userId);

    @Query("SELECT qr.phoneNumber, MAX(qr.score) as bestScore, COUNT(qr.id) as totalAttempts, MAX(qr.completedAt) as lastCompletedAt " +
            "FROM QuizResult qr " +
            "WHERE qr.phoneNumber is not null " +
            "GROUP BY qr.phoneNumber " +
            "ORDER BY MAX(qr.score) DESC, MAX(qr.completedAt) DESC")
    List<Object[]> findTop5UsersByBestScore(Pageable pageable);
}
