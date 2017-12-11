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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.ls.LSSerializer;

import com.revature.board.beans.Card;
import com.revature.board.beans.Lane;
import com.revature.board.repo.BoardRepo;
import com.revature.board.repo.CardRepo;
import com.revature.board.repo.LaneRepo;
import com.revature.board.repo.TaskRepo;
import com.revature.board.service.DisplayService;
import com.revature.board.service.UserNotFoundException;

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
	
	@Autowired
	private DisplayService serv;
	
	@Before
	public void setUp() {
		service = new DisplayService(null);
		
		boardRepoMock = mock(BoardRepo.class);
		laneRepoMock = mock(LaneRepo.class);
		cardRepoMock = mock(CardRepo.class);
		taskRepoMock = mock(TaskRepo.class);
		
		service.setBoardRepo(boardRepoMock);
		service.setLaneRepo(laneRepoMock);
		service.setCardRepo(cardRepoMock);
		service.setTaskRepo(taskRepoMock);
	}
	
	
	/*TestCard*/
	@Test
	public void testSaveCard() {
		Card card = new Card(1,1,1,"Mockito Drinks mock", "Dont drink Mockito");
		when(cardRepoMock.save(same(card))).thenReturn(card);
		
		Card returned = service.saveCard(card);
		
		verify(cardRepoMock, times(1)).save(card);
		verifyNoMoreInteractions(cardRepoMock);
		
		assertEquals(card, returned);
	}
	@Test
	@Rollback(true)
	public void testSaveCardJunit() {
		Card card = new Card(777,2,2,"Mockito is thirsty", "Please drink Mockito");
		serv.saveCard(card);
		
		List<Card> returned = serv.findByLaneId(777);
		assertEquals(card.getcDescription(), returned.get(0).getcDescription());
	}
	
	@Test
	public void testfindByLId() {
		Card card1 = new Card(2,2,2,"Mockito is thirsty", "Please drink Mockito");
		Card card2 = new Card(2,3,3,"HelloWorld", "JavaWorld");
		List<Card> ls = new ArrayList<Card>();
		ls.add(card1);
		ls.add(card2);
		when(cardRepoMock.findByLId(2)).thenReturn(ls);
		
		
		List<Card> returned = service.findByLaneId(2);
	
		verify(cardRepoMock, times(1)).findByLId(2);
		verifyNoMoreInteractions(cardRepoMock);
		
		assertEquals(ls, returned);
	}
	
	/*TestLane*/
	@Test
	public void testSaveLane() {
		Lane lane = new Lane(1,"TestLane");
		when(laneRepoMock.save(same(lane))).thenReturn(lane);
		
		Lane returned = service.saveLane(lane);
		
		verify(laneRepoMock, times(1)).save(lane);
		verifyNoMoreInteractions(laneRepoMock);
		
		assertEquals(lane, returned);
	}
	@Test
	@Rollback(true)
	public void testSaveLaneJunit() {
		Lane lane = new Lane(666,"TestLane");
		serv.saveLane(lane);
		
		List<Lane> returned = serv.findByBoardId(666);
		assertEquals(lane.getLaneTitle(), returned.get(0).getLaneTitle());
	}
//	@Test(expected=UserNotFoundException.class)
//	public void testSaveLane2() throws UserNotFoundException{
//		Lane lane = new Lane(555,1,"TestLane");
//		when(laneRepoMock.findByLaneId(555)).thenReturn(null);
//	
//		Lane returned = service.saveLane(lane);
//		
//		verify(laneRepoMock, times(1)).findByLaneId(555);
//		verifyNoMoreInteractions(laneRepoMock);
//		
//	}
	
	
	
}







