<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 상세</title>
    <style>
        .center {
            text-align: center;
        }
        table {
            border: 1px solid black;
            border-spacing: 0; /* cellspacing 대체 */
            border-collapse: collapse;
            width: 100%;
        }
        td {
            padding: 10px; /* cellpadding 대체 */
        }
        .header {
            background-color: orange;
            width: 70px;
        }
        .full-width {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="center">
        <h1>게시글 상세</h1>
        <hr>
        <form action="updateBoard" method="post">
            <input name="seq" type="hidden" value="${board.seq}" />
            <table>
                <tr>
                    <td class="header">제목</td>
                    <td align="left">
                        <input name="title" type="text" value="${board.title}" class="full-width"/>
                    </td>
                </tr>
                <tr>
                    <td class="header">작성자</td>
                    <td align="left">${board.writer}</td>
                </tr>
                <tr>
                    <td class="header">내용</td>
                    <td align="left">
                        <textarea name="content" cols="40" rows="10" class="full-width">${board.content}</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="header">등록일</td>
                    <td align="left">
                        <fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="글 수정" />
                    </td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="insertBoardView">글등록</a>&nbsp;&nbsp;&nbsp;
        <a href="deleteBoard?seq=${board.seq}">글삭제</a>&nbsp;&nbsp;&nbsp;
        <a href="getBoardList">글목록</a>
    </div>
</body>
</html>
