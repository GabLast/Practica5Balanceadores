<#include "../base.ftlh">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
    <body>
    <div class="container-fluid">
        <div class="row">
            <div class="container-fluid">
                <div class="row">
                    <div class="col md-1">
                    </div>
                    <div class="col-md-9 me-auto">
                        <#if listUsers??>
                            <div class="table-responsive" style="height: 600px">
                                <table class="table table-striped table-hover">
                                    <thead class="table-dark text-center">
                                    <tr>
                                        <th scope="col"><#if username??>${username}</#if></th>
                                        <th scope="col"><#if rol??>${rol}</#if></th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center table-bordered">
                                    <#list listUsers as u>
                                        <tr class="table-secondary">
                                            <td>${u.getUsername()}</td>
                                            <td>
                                                <#list u.getRoles() as rol>
                                                    ${rol.getRole()}<br>
                                                </#list>
                                            </td>
                                            <td>
                                                <div class="row justify-content-evenly">
                                                    <div class="col-md-12">
                                                        <form method="post" action="/admin/users/delete">
                                                            <input hidden type="text" value="${u.getUsername()}"
                                                                   name="username">
                                                            <button type="submit"
                                                                    class="btn btn-success btn-sm btn-eliminar"><span
                                                                        class="material-icons">delete</span></button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        <#else>
                            <div class="row justify-content-evenly">
                                <div class="col-md-4 offset-md-1">
                                    <h4 style="color: whitesmoke">No data</h4>
                                </div>
                            </div>
                        </#if>
                    </div>
                    <div class="col md-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>