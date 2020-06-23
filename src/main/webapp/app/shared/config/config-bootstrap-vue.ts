import bForm from 'bootstrap-vue/es/components/form/form';
import bFormInput from 'bootstrap-vue/es/components/form-input/form-input';
import bFormCheckbox from 'bootstrap-vue/es/components/form-checkbox/form-checkbox';
import bFormGroup from 'bootstrap-vue/es/components/form-group/form-group';
import bProgress from 'bootstrap-vue/es/components/progress/progress';
import bProgressBar from 'bootstrap-vue/es/components/progress/progress-bar';
import bPagination from 'bootstrap-vue/es/components/pagination/pagination';
import bButton from 'bootstrap-vue/es/components/button/button';
import bNavbar from 'bootstrap-vue/es/components/navbar/navbar';
import bNavbarNav from 'bootstrap-vue/es/components/navbar/navbar-nav';
import bNavbarBrand from 'bootstrap-vue/es/components/navbar/navbar-brand';
import bNavbarToggle from 'bootstrap-vue/es/components/navbar/navbar-toggle';
import bNavItem from 'bootstrap-vue/es/components/nav/nav-item';
import bNavItemDropdown from 'bootstrap-vue/es/components/nav/nav-item-dropdown';
import bCollapse from 'bootstrap-vue/es/components/collapse/collapse';
import bBadge from 'bootstrap-vue/es/components/badge/badge';
import bDropdown from 'bootstrap-vue/es/components/dropdown/dropdown';
import bDropdownItem from 'bootstrap-vue/es/components/dropdown/dropdown-item';
import bLink from 'bootstrap-vue/es/components/link/link';
import bAlert from 'bootstrap-vue/es/components/alert/alert';
import bModal from 'bootstrap-vue/es/components/modal/modal';
import bCard from 'bootstrap-vue/es/components/card/card';
import bCardHeader from 'bootstrap-vue/es/components/card/card-header';
import bCardText from 'bootstrap-vue/es/components/card/card-text';
import bCardBody from 'bootstrap-vue/es/components/card/card-body';
import bFormSelect from 'bootstrap-vue/es/components/form-select/form-select';

/*Directivas*/
import bModalDirective from 'bootstrap-vue/es/directives/modal/modal';
import bToggleDirective from 'bootstrap-vue/es/directives/toggle/toggle';

export function initBootstrapVue(vue) {
  vue.component('b-badge', bBadge);
  vue.component('b-dropdown', bDropdown);
  vue.component('b-dropdown-item', bDropdownItem);
  vue.component('b-link', bLink);
  vue.component('b-alert', bAlert);
  vue.component('b-modal', bModal);
  vue.component('b-button', bButton);
  vue.component('b-navbar', bNavbar);
  vue.component('b-navbar-nav', bNavbarNav);
  vue.component('b-navbar-brand', bNavbarBrand);
  vue.component('b-navbar-toggle', bNavbarToggle);
  vue.component('b-pagination', bPagination);
  vue.component('b-progress', bProgress);
  vue.component('b-progress-bar', bProgressBar);
  vue.component('b-form', bForm);
  vue.component('b-form-input', bFormInput);
  vue.component('b-form-group', bFormGroup);
  vue.component('b-form-checkbox', bFormCheckbox);
  vue.component('b-collapse', bCollapse);
  vue.component('b-nav-item', bNavItem);
  vue.component('b-nav-item-dropdown', bNavItemDropdown);
  vue.component('b-card-header', bCardHeader);
  vue.component('b-card', bCard);
  vue.component('b-card-text', bCardText);
  vue.component('b-card-body', bCardBody);
  vue.component('b-form-select', bFormSelect);
  /*Directivas*/
  vue.directive('b-modal', bModalDirective);
  vue.directive('b-toggle', bToggleDirective);
}
