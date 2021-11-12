<#include "../base.ftlh">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
        <script>
            function selectElement(id, valueToSelect) {
                let element = document.getElementById(id);
                element.value = valueToSelect;
            }
            <#if mock??>
                window.onload=selectElement(httpcode, ${mock.getStatusCode().getCode()});
                window.onload=selectElement(accessMethod, ${mock.getAccessMethod().getMethod()});
                window.onload=selectElement(charset, ${mock.getCharset().getDescription()});
                window.onload=selectElement(charset, ${mock.getContentType().getDescription()});
            </#if>
        </script>
    </#if>
</#macro>

<#macro page_body>
    <body>
    <div class="container-sm">
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <form method="post" action="/mock/edit" enctype="application/x-www-form-urlencoded">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><#if edit??>${edit} Mock</#if></h5>
                        </div>
                        <div class="modal-body">
                            <#if mock??>
                            <div class="mb-3">
                                <label for="idmock" class="form-label">ID:</label>
                                <input type="number" class="form-control" id="idmock" name="idmock" value="${mock.getIdMock()?long}" readonly required>
                            </div>
                            <div class="mb-3">
                                <label for="ruta" class="form-label"><#if ruta??>${ruta}</#if>:</label>
                                <input type="text" class="form-control" id="ruta" name="ruta" value="${mock.getRoute()}" required>
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label"><#if mockname??>${mockname}</#if>:</label>
                                <input type="text" class="form-control" id="name" name="name" value="${mock.getName()}"required>
                            </div>
                            <div class="mb-3">
                                <label for="httpcode" class="form-label"><#if statuscode??>${statuscode}</#if>:</label>
                                <select name="httpcode" id="httpcode" class="form-select" required>
                                    <#if listCodes??>
                                        <#list listCodes as code>
                                            <option value="${code.getCode()}">${code.getCode()} - ${code.getDescription()}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="accessMethod" class="form-label"><#if accessMethod??>${accessMethod}</#if>:</label>
                                <select  name="accessMethod" id="accessMethod" class="form-select" required>
                                    <#if listAccessMethod?has_content>
                                        <#list listAccessMethod as e>

                                            <option value="${e.getMethod()}">${e.getMethod()}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="charset" class="form-label"><#if charset??>${charset}</#if>:</label>
                                <select  name="charset" id="charset" class="form-select" required>
                                    <#if listCharset?has_content>
                                        <#list listCharset as e>

                                            <option value="${e.getDescription()}">${e.getDescription()}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="contentType" class="form-label"><#if contentType??>${contentType}</#if>:</label>
                                <select name="contentType" id="contentType" class="form-select" required>
                                    <#if listContentType?has_content>
                                        <#list listContentType as e>

                                            <option value="${e.getDescription()}">${e.getDescription()}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="header" class="form-label"><#if header??>${header}</#if>:</label>
                                <textarea class="form-control" <#if header??>placeholder="${keyvalue}"</#if> id="header"
                                          name="header" style="height: 200px" maxlength="255" <#if jsonFormat??>placeholder="${jsonFormat}"</#if>>${mock.getHttpHeaders()}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="body" class="form-label"><#if body??>${body}</#if>:</label>
                                <textarea class="form-control" id="body" name="body" style="height: 200px" maxlength="255">${mock.getResponseBody()}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="fechaexpire" class="form-label"><#if fechaexpire??>${fechaexpire}</#if>:</label>
                                <select name="fechaexpire" id="fechaexpire" class="form-select" required>
                                    <option value="hour">1 <#if hora??>${hora}</#if></option>
                                    <option value="day">1 <#if dia??>${dia}</#if></option>
                                    <option value="week">1 <#if semana??>${semana}</#if></option>
                                    <option value="month">1 <#if mes??>${mes}</#if></option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" class="btn-check" autocomplete="off" id="jwt" name="jwt" value="true">
                                <label for="jwt" class="btn btn-outline-primary"><#if jwt??>${jwt}</#if></label>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary btn-dark"><#if edit??>${edit}</#if></button>
                        </div>
                                <#else>
                                <p>No data :(</p>
                            </#if>

                    </div>
                </form>
            </div>
            <div class="col-md-3">
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
                <br>
                <br>
                <br>
            </div>
            <div class="col-md-4">

            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>
