<%@page contentType="text/html; charset=UTF-8"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>글 목록</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body th:align="center">
    <h1>게시글 목록</h1>
    <table th:align="center" border="1" th:cellpadding="0" th:cellspacing="0" th:width="700">
        <tr>
            <th bgcolor="orange" width="100">번호</th>
            <th bgcolor="orange" width="200">제목</th>
            <th bgcolor="orange" width="150">작성자</th>
            <th bgcolor="orange" width="150">등록일</th>
            <th bgcolor="orange" width="100">조회수</th>
        </tr>
      
        <tr th:each="board : ${boardList}">
            <td th:text="${board.seq}"></td>
            <td th:text="${board.title}"></td>
            <td th:text="${board.writer}"></td>
            <td th:text="${board.createDate}"></td>
            <td th:text="${board.cnt}"></td>
        </tr>
    </table>
    <br>
    <a href="insertBoard">새글 등록</a>
</body>
</html>
