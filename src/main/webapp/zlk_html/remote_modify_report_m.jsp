<script src="zlk_js/remote_register_j.js"></script>
<div class="bjui-pageContent">
    <div style="margin:0 3% 0 3%">
        <form id="register" data-toggle="validate" >
            <fieldset>
                <legend style="font-family:verdana;font-size:130%;color:orange">患者信息登记：</legend>
                <div class="bjui-row col-3">
                    <hr style="height:3px;">
                    <label class="row-label">患者姓名：</label>
                    <div class="row-input required">
                        <input type="text" name="PatName" class="PatName">
                    </div><br/>
                    <label class="row-label">性别：</label>
                    <div class="row-input required">
                        <input type="radio" name="PatGender" data-toggle="icheck" value="男" data-rule="checked" data-label="男&nbsp;&nbsp;&nbsp;">
                        <input type="radio" name="PatGender" data-toggle="icheck" value="女" data-label="女">
                    </div>
                    <label class="row-label">已检查类型：</label>
                    <div class="row-input required">
                        <select id="CheckType" name="CheckType" class="modality" data-toggle="selectpicker" data-width="150">
                            <option value = "CT">CT</option>
                            <option value = "CR">CR</option>
                            <option value = "DX">DX</option>
                            <option value = "MR">MR</option>
                            <option value = "US">US</option>
                        </select>
                    </div><br/>
                    <label class="row-label">出生日期：</label>
                    <div class="row-input required">
                        <input type="text" value="" data-toggle="datepicker" placeholder="点击选择日期" name="brithday" data-pattern="yyyy-MM-dd HH:mm:ss">
                    </div>
                    <label class="row-label">患者类型：</label>
                    <div class="row-input">
                        <input type="radio" name="PatType" data-toggle="icheck" value="门诊" data-label="门诊">
                        <input type="radio" name="PatType" data-toggle="icheck" value="住院" data-label="住院">
                        <input type="radio" name="PatType" data-toggle="icheck" value="急诊" data-label="急诊">
                        <input type="radio" name="PatType" data-toggle="icheck" value="其他" data-label="其他">
                    </div><br/>
                    <label class="row-label">身份证号：</label>
                    <div class="row-input">
                        <input type="text" name="PatIDCard" class="PatIDCard">
                    </div>
                    <label class="row-label">联系方式：</label>
                    <div class="row-input">
                        <input type="text" name="tel" class="tel">
                    </div><br/>
                    <label class="row-label">住址：</label>
                    <div class="row-input">
                        <input type="text" name="address" class="address">
                    </div>
                    <label class="row-label">医保号：</label>
                    <div class="row-input">
                        <input type="text" name="societyID" class="societyID">
                    </div><br/>
                </div>
                <hr style="height:3px;">
                <div>
                    <div style="float:left;margin-left:10%">
                        <p id="qianzhui" style="float:left">生成的远程检查号：<p id="r_checknum" style="float:left"></p>
                    </div>
                    <div style="margin:0 0 1% 15%;float:left">
                        <a id="uploadDicom" href="#" type="hidden">上传图像</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="reset" class="btn-orange" form="register">重置表单</button></li>
        <li><button type="submit" class="btn-default" data-icon="save" id="remoteDiagReg">生成患者ID并登记</button></li>
    </ul>
</div>
