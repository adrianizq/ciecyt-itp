<template>
    <b-navbar toggleable="md" type="dark" class="bg-primary">
        <div class="jh-logo-container float-left">
            <b-navbar-toggle right class="jh-navbar-toggler d-lg-none float-right" href="javascript:void(0);" data-toggle="collapse" target="header-tabs" aria-expanded="false"
                             aria-label="Toggle navigation">
                <font-awesome-icon icon="bars"/>
            </b-navbar-toggle>
            <b-navbar-brand class="logo float-left" b-link to="/">
                <span class="logo-img"></span>
                <span v-text="$t('global.title')" class="navbar-title">ciecyt</span> <span class="navbar-version">{{version}}</span>
            </b-navbar-brand>
        </div>
        <b-collapse is-nav id="header-tabs">

            <b-navbar-nav class="ml-auto"   v-if="hasAnyAuthority('ROLE_ADMIN')">
                <template v-for="menu in menus">
                    <b-nav-item :to="menu.url" exact v-if="!menu.children.length">
                        <span>
                            <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                            <span>
                                {{menu.nombre}}
                            </span>
                        </span>
                    </b-nav-item>

                    <b-nav-item-dropdown align="left"
                        right
                        href="javascript:void(0);"
                        :id="menu.id.toString()"
                        :class="{'router-link-active': subIsActive(menu.url)}"
                        active-class="active"
                        class="pointer"
                       
                        v-else>
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                        <span>
                            {{ menu.nombre }}
                        </span>
                    </span>
                        <template v-for="submenu in menu.children">
                            <b-dropdown-item :to="submenu.url" tag="b-dropdown-item" v-if="isUrl(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>

                            <b-dropdown-item v-if="!isUrl(submenu.url)" @click="actionMenu(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>
                        </template>


                    </b-nav-item-dropdown>
                </template>

           
            </b-navbar-nav>


            <!-- Para rol Ciecyt -->
             <b-navbar-nav class="ml-auto"   v-if="hasAnyAuthority('ROLE_CIECYT')">
                <template v-for="menu in menusCiecyt">
                    <b-nav-item :to="menu.url" exact v-if="!menu.children.length">
                        <span>
                            <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                            <span>
                                {{menu.nombre}}
                            </span>
                        </span>
                    </b-nav-item>

                    <b-nav-item-dropdown align="left"
                        right
                        href="javascript:void(0);"
                        :id="menu.id.toString()"
                        :class="{'router-link-active': subIsActive(menu.url)}"
                        active-class="active"
                        class="pointer"
                       
                        v-else>
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                        <span>
                            {{ menu.nombre }}
                        </span>
                    </span>
                        <template v-for="submenu in menu.children">
                            <b-dropdown-item :to="submenu.url" tag="b-dropdown-item" v-if="isUrl(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>

                            <b-dropdown-item v-if="!isUrl(submenu.url)" @click="actionMenu(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>
                        </template>


                    </b-nav-item-dropdown>
                </template>

              
            </b-navbar-nav> 
            
            <!-- Para rol Jurado-->
             <b-navbar-nav class="ml-auto"   v-if="hasAnyAuthority('ROLE_JURADO')">
                <template v-for="menu in menusJurado">
                    <b-nav-item :to="menu.url" exact v-if="!menu.children.length">
                        <span>
                            <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                            <span>
                                {{menu.nombre}}
                            </span>
                        </span>
                    </b-nav-item>

                    <b-nav-item-dropdown align="left"
                        right
                        href="javascript:void(0);"
                        :id="menu.id.toString()"
                        :class="{'router-link-active': subIsActive(menusJurado.url)}"
                        active-class="active"
                        class="pointer"
                       
                        v-else>
                    <span slot="button-content" class="navbar-dropdown-menu">
                        <font-awesome-icon :icon="menu.icono || 'asterisk'"/>
                        <span>
                            {{ menu.nombre }}
                        </span>
                    </span>
                        <template v-for="submenu in menu.children">
                            <b-dropdown-item :to="submenu.url" tag="b-dropdown-item" v-if="isUrl(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>

                            <b-dropdown-item v-if="!isUrl(submenu.url)" @click="actionMenu(submenu.url)">
                                <font-awesome-icon :icon="submenu.icono || 'asterisk'"/>
                                <span>{{ submenu.nombre }}</span>
                            </b-dropdown-item>
                        </template>


                    </b-nav-item-dropdown>
                </template>

              
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script lang="ts" src="./jhi-navbar.component.ts">
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    /* ==========================================================================
        Navbar
        ========================================================================== */
    .navbar-version {
        font-size: 10px;
    }


    @media screen and (min-width: 768px) {
        .jh-navbar-toggler {
            display: none;
        }
    }

    @media screen and (min-width: 768px) and (max-width: 1150px) {
        span span {
            display: none;
        }
    }

    @media screen and (max-width: 767px) {
        .jh-logo-container {
            width: 100%;
        }
    }

    .navbar-title {
        display: inline-block;
        vertical-align: middle;
    }

    /* waiting for bootstrap fix bug on nav-item-dropdown a:active
    https://github.com/bootstrap-vue/bootstrap-vue/issues/2219
    */
    nav li.router-link-active .navbar-dropdown-menu {
        cursor: pointer;
    }

    /* ==========================================================================
        Logo styles
        ========================================================================== */
    .navbar-brand.logo {
        padding: 5px 15px;
    }

    .logo .logo-img {
        height: 45px;
        display: inline-block;
        vertical-align: middle;
        width: 70px;
    }

    .logo-img {
        height: 100%;
        background: url("../../../content/images/logo-jhipster.png") no-repeat center center;
        background-size: contain;
        width: 100%;
    }
</style>
