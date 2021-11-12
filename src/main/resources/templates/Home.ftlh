<#include "base.ftlh">
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
                        <div class="col-md-12">
                            <br>
                            <h3 class="text-light text-center">Session ID: ${session} || Port: ${port?c} || Number of Visits: ${contador?c}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>