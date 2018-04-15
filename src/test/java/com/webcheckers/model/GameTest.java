
package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.appl.MoveVerifier;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */

@Tag("Model-tier")
public class GameTest {
    //attributes
    private  static final  String name = "Hongda Lin";
    private  static final  String opponent = "Karthik";
    private  static final  int  Row  = 1;
    private  static final  int  Cell  = 3;
    private static final int row1 = 0;
    private static final int cell1 = 4;
    private  static final  int whoseTurn = 0;
    private ArrayList< Move > validatedMoves;
    final Board board = new Board();
    final Position start = new Position(Row,Cell);
    final Position end = new Position(row1,cell1);
    final Move move = new Move(start,end);
    final Move move1 = new Move(start,end);
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    private PlayerLobby playerLobby = new PlayerLobby();
    private final HashMap<String,Object> player = new HashMap<>();
    private Game game = new Game(board,player1,player2);
    private Game.GameState gameState  = Game.GameState.in_progress;
    private MoveVerifier moveVerifier = new MoveVerifier();

    @Test
    public void addMove(){
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.SIMPLE);
        board.getSpace(start).setPiece(piece);
//        game.getPieceAt(row1,cell1).setPieceType(Piece.PieceType.KING);
//        game.getPieceAt(row1,cell1).setColor(Piece.Color.RED);
//        game.addVerifiedMove(move);
//        game.addVerifiedMove(move1);
    }
    @Test
    public void  test_Game(){

        assertNotNull(game, "The game is exist");
    }
    @Test
    public void test_GetPlayer1()
    {
        assertEquals(game.getPlayerOne(),player1);
        assertNotNull(game.getPlayerOne());
    }
    @Test
    public void test_GetPlayer2(){
        assertEquals(game.getPlayerTwo(),player2);
        assertNotNull(game.getPlayerTwo());
    }
    @Test
    public void test_RemovePlayer1(){
        game.removePlayerOne();
        assertNull(game.getPlayerOne());
    }
    @Test
    public void test_RemovePlayer2(){
        game.removePlayerTwo();
        assertNull(game.getPlayerTwo());
    }
    @Test
    public void test_SwitchTrun(){
        game.switchTurn();
        assertNotNull(game.getWhoseTurn());
    }
    @Test
    public void test_GetwhoseTurn(){
        assertNotNull(game.getWhoseTurn());
        assertEquals(game.getWhoseTurn(),whoseTurn);
    }
    @Test
    public void test_SetGamestate(){
        game.setGameState(gameState);
    }
    @Test
    public void test_isComplete(){
        final Game.GameState gameState1 =  Game.GameState.complete;
        game.setGameState(gameState1);
        assertTrue(game.isComplete());
    }
    @Test
    public void test_getVerifier(){
        assertNotNull(game.getMoveVerifier());
    }
    @Test
    public void test_GetSpaceAT1(){
        assertNotNull(game.getSpaceAt(start));
        assertEquals(game.getSpaceAt(start),board.getSpace(Row,Cell));
    }
    @Test
    public void test_GetSpaceAT2(){
        assertNotNull(game.getSpaceAt(Row,Cell));
    }
    @Test
    public void test_GetBoard(){
        assertNotNull(game.getBoard());
        assertEquals(game.getBoard(),board);
    }
    @Test
    public void test_GetContains(){
        playerLobby.addPlayer(player1,player);
        playerLobby.addPlayer(player2,player);
        game.getPlayerOne();
        game.getPlayerTwo();
        assertTrue(game.contains(player1));
        assertTrue(game.contains(player2));
    }
    @Test
    public void test_outOfValidatedMove(){
        assertTrue(game.outOfVerifiedMoves());
    }
//    @Test
//    public void test_backupValidatedMove(){
//        assertEquals(game.backupVerifiedMove(),move);
//   }
//    @Test
//    public void test_getFirstValidateMove(){
//        assertEquals(game.getFirstVerifiedMove(),move);
//    }
    @Test
    public void test_noMovesAvailableForPlayerOne(){
        assertTrue(game.anyMovesAvailableForPlayerOne());
    }
    @Test
    public void test_noMovesAvailableForPlayerTwo(){
        assertTrue(game.anyMovesAvailableForPlayerTwo());
    }
    @Test
    public void test_Euqals(){
        playerLobby.addPlayer(player1,player);
        playerLobby.addPlayer(player2,player);
        assertFalse(game.equals(player1));
    }
    @Test
    public void  test_isThereAKingPieceAt(){
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        board.getSpace(0,1).setPiece(piece);
        assertTrue(game.isThereAKingPieceAt(0,1));
    }
    @Test
    public void  test_getPieceAt1(){
        assertNotNull(game.getPieceAt(row1,cell1));
    }
    @Test
    public void test_getPieceAt2(){
        assertNotNull(game.getSpaceAt(start));
    }
}

