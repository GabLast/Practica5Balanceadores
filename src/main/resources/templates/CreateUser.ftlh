<#include "base.ftlh">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
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
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
                <form method="post" action="/user/create" enctype="application/x-www-form-urlencoded">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><#if createuser??>${createuser}</#if></h5>
                        </div>
                        <div class="modal-body">
                            <#if _csrf??> <#--validando que no sea nula, si lo es, está deshabilitado el csrf -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </#if>
                            <div class="mb-3">
                                <label for="username" class="form-label"><#if username??>${username}</#if>:</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label"><#if password??>${password}</#if>:</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label"><#if rol??>${rol}</#if>:</label>
                                <br>
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                    <input type="radio" class="btn-check" name="rol" id="userRol" autocomplete="off"
                                           value="ROLE_USER" checked>
                                    <label class="btn btn-outline-primary" for="userRol">User</label>

                                    <input type="radio" class="btn-check" name="rol" id="adminRol" autocomplete="off"
                                           value="ROLE_ADMIN">
                                    <label class="btn btn-outline-primary" for="adminRol">Admin</label>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit"
                                    class="btn btn-primary btn-dark"><#if create??>${create}</#if></button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4">
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>