package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // attributePaths 로딩 설정을 변경하고 싶은 속성의 이름을 배열로 명시
    // type EntityGraph를 어떤 방식으로 적용할 것인지를 설정
    // fatch 속성 attributePaths에 명시한 속성은 eager로 처리 나머지는 lazy 처리
    // load 속성  attributePaths에 명시한 속성은 eager로 처리 나머지는 엔티티 클래스에 명시되거나 기본 방식 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);
}
