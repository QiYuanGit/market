<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>

</head>
<body>

<form id="itemForm"  action="${pageContext.request.contextPath }/user/uploadPic" method="post" enctype="multipart/form-data">
        <tr>
            <td>商品图片</td>
            <td>
                <input type="file"  name="file"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>