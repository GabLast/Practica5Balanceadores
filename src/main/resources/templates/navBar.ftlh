<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <#if inicio??>
                ${inicio}
            </#if>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" style="margin-top: 5px;" id="navbarNav">
            <span class="navbar-text">
                <#if usuario??>
                    <#if welcome??>
                        ${welcome}, ${usuario.getUsername()}
                    </#if>
                </#if>
            </span>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <#if gestionmocks??>
                            ${gestionmocks}
                        </#if>
                    </a>
                    <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/mock/list">
                                <#if viewMocks??>
                                    ${viewMocks}
                                </#if>
                            </a></li>
                        <li><a class="dropdown-item" href="/mock/create">
                                <#if crearMock??>
                                    ${crearMock}
                                </#if>
                            </a></li>
                    </ul>
                </li>
                <#if usuario??>
                    <#if admin == true>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                <#if admintools??>
                                    ${admintools}
                                </#if>
                            </a>
                            <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdownMenuLink">
                                <li class="nav-item">
                                    <a class="dropdown-item" href="/admin/users/list"><#if listarusers??>${listarusers}</#if></a>
                                </li>
                                <li class="nav-item">
                                    <a class="dropdown-item" href="/admin/mocks/list"><#if viewMocks??>${viewMocks}</#if></a>
                                </li>
                            </ul>
                        </li>
                    </#if>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout"><#if logout??>${logout}</#if></a>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/sign-up"><#if signup??>${signup}</#if></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/auth"><#if login??>${login}</#if></a>
                    </li>
                </#if>

            </ul>
        </div>
    </div>
</nav>