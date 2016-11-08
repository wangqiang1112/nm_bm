<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="uploadimg.aspx.cs" Inherits="SJBFramework.WebManage.admin.javascripts.ueditor.dialogs.image.uploadimg" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>上传图片</title>
    <style type="text/css">
        body
        {
            font-size:12px;
            padding:20px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:FileUpload ID="fileUpload" runat="server" />
        <asp:Button ID="btnUpload" runat="server" Text="上传图片" 
            onclick="btnUpload_Click" />
    </div>
    </form>
</body>
</html>
