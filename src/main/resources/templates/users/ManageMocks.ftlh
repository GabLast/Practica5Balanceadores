<#include "../base.ftlh">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
    <body>
    <div class="container-fluid">
        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-1">
                        </div>
                        <div class="col-md-10 me-auto">
                            <div class="table-responsive" style="height: 600px">
                                <table class="table table-striped table-hover">
                                    <thead class="table-dark text-center">
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col"><#if mockname??>${mockname}</#if></th>
                                        <th scope="col"><#if ruta??>${ruta}</#if></th>
                                        <th scope="col"><#if statuscode??>${statuscode}</#if></th>
                                        <th scope="col"><#if charset??>${charset}</#if></th>
                                        <th scope="col"><#if contentType??>${contentType}</#if></th>
                                        <th scope="col"><#if accessMethod??>${accessMethod}</#if></th>
                                        <th scope="col"><#if header??>${header}</#if></th>
                                        <th scope="col"><#if body??>${body}</#if></th>
                                        <th scope="col"><#if fechacreate??>${fechacreate}</#if></th>
                                        <th scope="col"><#if fechaexpire??>${fechaexpire}</#if></th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center table-bordered">
                                    <#if listmocks?has_content>
                                        <#list listmocks as es>
                                            <tr class="table-secondary">
                                                <td>${es.getIdMock()}</td>
                                                <td>${es.getName()}</td>
                                                <td>${es.getRoute()}</td>
                                                <td>${es.getStatusCode().getCode()}</td>
                                                <td>${es.getCharset().getDescription()}</td>
                                                <td>${es.getContentType().getDescription()}</td>
                                                <td>${es.getAccessMethod().getMethod()}</td>
                                                <td>${es.getHttpHeaders()}</td>
                                                <td>${es.getResponseBody()}</td>
                                                <td>${es.getDateCreate()?datetime}</td>
                                                <td>${es.getExpirationDate()?datetime}</td>
                                                <td>
                                                    <div class="row">
                                                        <div class="col">
                                                            <form method="get" action="/mock/edit"
                                                                  enctype="application/x-www-form-urlencoded">
                                                                <input type="number" name="idmock"
                                                                       value="${es.getIdMock()}" hidden required/>
                                                                <button class="btn btn-sm btn-custom1"
                                                                        type="submit">
                                                                    <span class="material-icons">edit</span>
                                                                </button>
                                                            </form>
                                                        </div>
                                                        <div class="col">
                                                            <form method="post" action="/mock/delete"
                                                                  enctype="application/x-www-form-urlencoded">
                                                                <input type="number" name="idmock"
                                                                       value="${es.getIdMock()}" hidden required/>
                                                                <button class="btn btn-sm btn-eliminar"
                                                                        type="submit">
                                                                    <span class="material-icons">clear</span>
                                                                </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </#list>
                                    <#else>
                                        <p style="color: whitesmoke">${empty}</p>
                                    </#if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col md-1">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>