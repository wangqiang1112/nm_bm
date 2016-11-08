using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SJBFramework.PageControl;
using SJBFramework.PageControl.FileOperate;

namespace SJBFramework.WebManage.admin.javascripts.ueditor.dialogs.image
{
    public partial class uploadimg : AdminPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnUpload_Click(object sender, EventArgs e)
        {
            string showUrl = "";
            string saveUrl = "";
            string typeId = "AdminEditor";
            bool isCache = false;
            EUploadState state = UploadFile.SaveFile(fileUpload.PostedFile, typeId, isCache, out showUrl, out saveUrl);
            if (state == EUploadState.Successful)
            {
                try
                {
                    FileHandle.FileSend(saveUrl, typeId, isCache, true);
                }
                catch (Exception err)
                {
                    SJBFramework.BLL.Sys_Log_PageError.Instance().WriteLog(err.Message, err.Source, Request.RawUrl, err.StackTrace, err.TargetSite.ToString(), SJBFramework.BLL.Sys_Log_PageError.EPageErrorType.System);
                    state = EUploadState.Failure;
                }
                this.Page.RegisterClientScriptBlock("backimg", "<script>opener.document.getElementById('url').value='" + showUrl + "';window.close();</script>");
            }
            else
            {
                this.Page.RegisterClientScriptBlock("backimg", "<script>alert('上传失败，失败状态：" + state.ToString() + "');</script>");
            }
            //json = "{\"imgurl\":\"" + showUrl + "\",\"saveurl\":\"" + saveUrl + "\",\"state\":\"" + ((int)state) + "\"}";
        }
    }
}