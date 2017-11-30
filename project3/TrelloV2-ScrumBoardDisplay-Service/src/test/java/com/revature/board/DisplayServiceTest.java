package com.revature.board;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.board.beans.Card;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;
import com.revature.board.repo.TaskRepo;
import com.revature.board.service.DisplayService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TrelloV2ScrumBoardDisplayServiceApplication.class)
public class DisplayServiceTest {
	
	@Autowired
	private DisplayService service;
	@Autowired
	private BoardRepo boardRepoMock;
	@Autowired
	private LaneRepo laneRepoMock;
	@Autowired
	private CardRepo cardRepoMock;
	@Autowired
	private TaskRepo taskRepoMock;
	
	@Before
	public void setUp() {
		service = new DisplayService();
		
		boardRepoMock = mock(BoardRepo.class);
		laneRepoMock = mock(LaneRepo.class);
		cardRepoMock = mock(CardRepo.class);
		taskRepoMock = mock(TaskRepo.class);
		
		service.setBoardRepo(boardRepoMock);
		service.setLaneRepo(laneRepoMock);
		service.setCardRepo(cardRepoMock);
		service.setTaskRepo(taskRepoMock);
	}
	
	@Test
	public void testCardSave() {
		Card card = new Card(1,1,1,"Mockito Drinks mock", "Dont drink Mockito");
		when(cardRepoMock.save(same(card))).thenReturn(card);
		
		Card returned = service.saveCard(card);
		
		assertEquals(card, returned);
	}
}






