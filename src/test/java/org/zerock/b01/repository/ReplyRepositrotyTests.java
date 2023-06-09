package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositrotyTests {


    @Autowired
    private ReplyRepository replyRepositroy;

    @Test
    public void testInsert(){

        //실제db에 있는 bno
        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder().board(board).replyText("댓글.....").replyer("replayer1").build();

        replyRepositroy.save(reply);

    }

    @Test
    public void testBoardReplies(){
        Long bno = 100L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result =replyRepositroy.listOfBoard(bno, pageable);

        result.getContent().forEach(reply -> {log.info(reply);});

    }




}
