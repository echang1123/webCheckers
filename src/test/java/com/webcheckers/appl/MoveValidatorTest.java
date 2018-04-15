/*
package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.model.*;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
*/
/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 *//*

@Tag("Appl-tier")
public class MoveValidatorTest {
    //Attributes

    private static final String name = "Hongda Lin";
    private static final String opponent = "Karthik";
    private static final int Row = 2;
    private static final int Cell = 2;
    private static final int row = 0;
    private static final int col = 0;
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    final Position start = new Position(Row,Cell);
    final Position end  = new Position(row,col);
    final Board board = new Board();
    final Space space = new Space(0,null,false);
    final Game game = new Game(board, player1, player2);
    final MoveValidator moveValidator = new MoveValidator();
    final Move move = new Move(start,end);

    @Test
    public void test_MoveValidator() {
        assertNotNull(moveValidator);
    }
    @Test
    public void test_isNormalMoveAvailable(){
        assertTrue(moveValidator.isNormalMoveAvailable(game,Row,Cell,board));
    }
    @Test
    private boolean isWithinBounds(int row, int col) {
        return ( ( row <= 7 ) && ( row >= 0 ) && ( col <= 7 ) && ( col >= 0 ) );
    }
    @Test
    private boolean isSimpleMove( Game game, Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        if( game.getWhoseTurn() == 0 ) {
            return ( end.getRow() - start.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }
        else {
            return ( start.getRow() - end.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }
    }
    @Test
    private boolean isKingSimpleMove( Game game, Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Space space = game.getBoard().getSpace( start.getRow(), start.getCell() );
        return false;
    }
//    @Test
//    public void test_isKingMoveAvailable(){
//        ;
//
//        assertTrue(moveValidator.isKingMoveAvailable(game,Row,Cell,board));
//    }
    @Test
    public void test_singleJumpAvailable(){
        assertFalse(moveValidator.singleJumpAvailable(game,Row,Cell,board));
    }
//    @Test
//    public void test_is(){
//        space.setPiece(new Piece(Piece.PieceType.KING, Piece.Color.RED));
//        assertFalse(moveValidator.kingJumpAvailable(game,Row,Cell,board));
//    }
    @Test
    public void test_validate(){
        assertFalse(moveValidator.validate(game,move));
    }

}
*/
