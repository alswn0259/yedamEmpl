package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InputDAO {
	static Connection conn = null;
	//삭제하기
	public static void deleteBoard(BoardVO vo) {
		String sql = "delete from input_board where board_no = ?";
		conn = DBCon.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getBoardNo());
			int r = stmt.executeUpdate();
			System.out.println(r + "건 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} //end of finally
		
	}
	
	//수정하기
	public static void updateBoard(BoardVO vo) {
		String sql = "update input_board "
				+ " set publicity = ?,"
				+ " exit_date = ?," 
				+ " contents = ?"
				+ " where board_no = ?";
		conn = DBCon.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getPublicity());
			stmt.setString(2, vo.getExitDate());
			stmt.setString(3, vo.getContents());
			stmt.setInt(4, vo.getBoardNo());
			int r = stmt.executeUpdate();
			System.out.println(r + "건 변경되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} //end of finally
	} //end of updateBoard()
	
	public static BoardVO beforeBoard() {
		conn = DBCon.getConnection();
		String sql = "select * from input_board where board_no = ?"
				+ " order by board_no";
		Statement stmt = null;
		ResultSet rs = null;
		BoardVO beforeBoard = null;
		ObservableList<BoardVO> boardList = FXCollections.observableArrayList();
		try {
			stmt = conn.createStatement();
			//위에ㅓㄲ..
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContents(rs.getString("contents"));
				vo.setExitDate(rs.getString("exit_date"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setPublicity(rs.getString("publicity"));
				vo.setTitle(rs.getString("title"));
				boardList.add(vo);
			}
			for(int i=0; i<boardList.size(); i++) {
				if(boardList.get(i).getBoardNo() == )
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//리스트 가져오기
	public static ObservableList<BoardVO> boardList() {
		String sql = "select * from input_board order by board_no";
		Statement stmt = null;
		ResultSet rs = null;
		//fxcollections가 가지고 있는 정적매소드로 변수 선언 한것
		ObservableList<BoardVO> boardList = FXCollections.observableArrayList();
		conn = DBCon.getConnection(); // Connection  객체 생성.
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContents(rs.getString("contents"));
				vo.setExitDate(rs.getString("exit_date"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setPublicity(rs.getString("publicity"));
				vo.setTitle(rs.getString("title"));
				boardList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return boardList;
	}
	
	//추가하기
	public static void InsertBoard(InputBoardVO bo) {
		PreparedStatement pstmt = null;
		String sql = "insert into input_board values"
				+ "((select nvl(max(board_no),0)+1 from input_board),?,?,?,?,?)";
				conn = DBCon.getConnection();
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bo.getTitle());
					pstmt.setString(2, bo.getPasswd());
					pstmt.setString(3, bo.getPublicity());
					pstmt.setString(4, bo.getExitDate());
					pstmt.setString(5, bo.getContents());
					int r = pstmt.executeUpdate();
					System.out.println(r + "건 입력됨.");
				} catch(SQLException e) {
					
					e.printStackTrace();
				} finally {
					if(pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if( conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} //end of final
	}
}
