package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;
import boardgame.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;

    private final List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.RED;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCols()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (isCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        Color opponentPlayer = opponent(currentPlayer);

        if (isCheck(opponentPlayer)) {
            if (isCheckMate(opponentPlayer)) {
                checkMate = true;
            }
        }
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position pos) {
        if (!board.thereIsPiece(pos)) {
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece)board.piece(pos)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(pos).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible move on source position");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("There is no possible move on target position");
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position source = sourcePosition.toPosition();
        validateSourcePosition(source);
        return board.piece(source).possibleMoves();
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove((ChessPiece)capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add((ChessPiece)capturedPiece);
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
    }

    private Color opponent(Color color) {
        return (color == Color.RED) ? Color.BLUE : Color.RED;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> x.getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean isCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> x.getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getCol()]) {
                check = true;
                return true;
            }
        }
        check = false;
        return false;
    }

    private boolean isCheckMate(Color color) {
        if (!isCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> x.getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = isCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void initialSetup() {
        placeNewPiece('h', 1, new Rook(board, Color.RED));
        placeNewPiece('c', 1, new Bishop(board, Color.RED));
        placeNewPiece('e', 1, new King(board, Color.RED));
        placeNewPiece('f', 1, new Bishop(board, Color.RED));
        placeNewPiece('a', 1, new Rook(board, Color.RED));
        placeNewPiece('a', 2, new Pawn(board, Color.RED));
        placeNewPiece('b', 2, new Pawn(board, Color.RED));
        placeNewPiece('c', 2, new Pawn(board, Color.RED));
        placeNewPiece('d', 2, new Pawn(board, Color.RED));
        placeNewPiece('e', 2, new Pawn(board, Color.RED));
        placeNewPiece('f', 2, new Pawn(board, Color.RED));
        placeNewPiece('g', 2, new Pawn(board, Color.RED));
        placeNewPiece('h', 2, new Pawn(board, Color.RED));

        placeNewPiece('e', 8, new King(board, Color.BLUE));
        placeNewPiece('a', 8, new Rook(board, Color.BLUE));
        placeNewPiece('c', 8, new Bishop(board, Color.BLUE));
        placeNewPiece('h', 8, new Rook(board, Color.BLUE));
        placeNewPiece('f', 8, new Bishop(board, Color.BLUE));
        placeNewPiece('a', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('b', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('c', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('d', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('e', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('f', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('g', 7, new Pawn(board, Color.BLUE));
        placeNewPiece('h', 7, new Pawn(board, Color.BLUE));
    }
}
