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
                    <form method="post" action="/user/auth" enctype="application/x-www-form-urlencoded">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"><#if login??>${login}</#if></h5>
                            </div>
                            <div class="modal-body">
                                <#if _csrf??> <#--validando que no sea nula, si lo es, está deshabilitado el csrf -->
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </#if>
                                <div class="mb-3">
                                    <label for="username" class="form-label"><#if username??>${username}</#if></label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label"><#if password??>${password}</#if></label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <#if error.isPresent()>
                                    <div class="mb-3">
                                        <p class="form-label" style="color:darkred"><#if noUser??>${noUser}</#if></p>
                                    </div>
                                </#if>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary btn-dark"><#if send??>${send}</#if></button>
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