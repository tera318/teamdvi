package com.example.zikosyoukaiWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamappApplication {

	private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "doraemon";

	public static void main(String[] args) {
		SpringApplication.run(TeamappApplication.class, args);

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = conn.createStatement()) {

			// クエリの実行例（ここではSELECT文を実行しています）
			// 使用可能◎
			// StringBuilder sql = new StringBuilder();
			// sql.append("insert into users values");
			// sql.append("(1,'test','テスト','2025-06-19','てすと')");
			// statement.executeUpdate(sql.toString());

			System.out.println("データベースに正常に接続しました。");

		} catch (SQLException e) {
			System.err.println("データベースの接続または操作中にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// try-with-resources を使用しているため、ここでは明示的なクローズは不要です
			System.out.println("データベース接続処理を終了しました。");
		}
	}
}