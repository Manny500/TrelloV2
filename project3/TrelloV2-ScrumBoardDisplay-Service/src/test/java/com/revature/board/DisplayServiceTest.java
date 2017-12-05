package com.revature.board;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.ls.LSSerializer;

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
		
		verify(cardRepoMock, times(1)).save(card);
		verifyNoMoreInteractions(cardRepoMock);
		
		assertEquals(card, returned);
	}
	@Test
	public void testfindByLId() {
		//int lId, int cVerify, int cWorth, String cTitle, String cDescription
		Card card1 = new Card(2,2,2,"Mockito is thirsty", "Please drink Mockito");
		Card card2 = new Card(2,3,3,"HelloWorld", "JavaWorld");
		List<Card> ls = new ArrayList<Card>();
		List<Card> ls2 = new ArrayList<Card>();
		ls.add(card1);
		ls.add(card2);
		when(cardRepoMock.findByLId(2)).thenReturn(ls);
		
		
		List<Card> returned = service.findByLaneId(2);
	
		verify(cardRepoMock, times(1)).findByLId(2);
		verifyNoMoreInteractions(cardRepoMock);
		
		assertEquals(ls, returned);
	}
}







