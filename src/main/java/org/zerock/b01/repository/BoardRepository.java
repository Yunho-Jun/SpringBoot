package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    //실무에서 많이 사용되지 않음.
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

    //jpql
    @Query("select b from Board b where b.title like concat('%',:keyword,'%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);

    //조인과 같이 복잡한 쿼리를 실행기능
    //원하는 속성들만 추출해서 object[]처리를 하거나 DTO로 처리할수 있는 기능
    //nativeQuery 속성값을 true로 지정해서 특정 데이터베이스에서 동작하는 sql을 사용하는 기능

    @Query(value = "select now()",nativeQuery = true)
    String getTime();



}
