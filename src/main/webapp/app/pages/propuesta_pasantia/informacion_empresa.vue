<template>
  <div class="row">
    <div class="col-sm-4">
      <menu-lateral-pasantia :proyectoId="$route.params.proyectoId"></menu-lateral-pasantia>
    </div>
    <div class="col-sm-8">
      <form @submit.prevent="save()">
        <div class="row">
          <div class="col-12">


          
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-convenio">Número del Convenio ITP - Empresa </label>
              <input
                type="num"
                class="form-control"
                name="convenio-empresa"
                id="informacion-pasantia-convenio-empresa"
                v-model="informacionPasantia.convenio"
                :class="{
                  'is-invalid': $v.informacionPasantia.convenio.$error,
                  'is-valid': !$v.informacionPasantia.convenio.$invalid
                }"
                placeholder="Número del convenio con la empresa que realizará la pasantía"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.convenio.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.convenio.between">
                Se espera un valor numérico entre {{ $v.informacionPasantia.convenio.$params.between.min }} y
                {{ $v.informacionPasantia.convenio.$params.between.max }} .
              </div>
            </div>
    
            <div class="form-group">
              <label
                class="form-control-label "
                v-text="$t('ciecytApp.informacionPasantia.duracionHoras')"
                for="informacion-pasantia-duracion-horas"
                >Duración de la pasantía en horas</label
              >
              <input
                type="number"
                class="form-control"
                name="duracion-horas"
                id="informacion-pasantia-duracion-horas"
                v-model.number="informacionPasantia.duracionHoras"
                :class="{
                  'is-invalid': $v.informacionPasantia.duracionHoras.$error,
                  'is-valid': !$v.informacionPasantia.duracionHoras.$invalid
                }"
                placeholder="Horas de pasantía: tecnico 480, tecnólogo 640, profesional 880"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.duracionHoras.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.duracionHoras.between">
                Se espera un valor numerico entre {{ $v.informacionPasantia.duracionHoras.$params.between.min }} y
                {{ $v.informacionPasantia.duracionHoras.$params.between.max }} .
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " v-text="$t('ciecytApp.informacionPasantia.direccion')" for="informacion-pasantia-direccion"
                >Direccion
              </label>
              <input
                type="text"
                class="form-control"
                name="direccion"
                id="informacion-pasantia-direccion"
                v-model="informacionPasantia.direccion"
                :class="{
                  'is-invalid': $v.informacionPasantia.direccion.$error,
                  'is-valid': !$v.informacionPasantia.direccion.$invalid
                }"
                placeholder="Ingrese la direccion donde realizará la pasantía"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccion.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccion.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.direccion.$params.minLength.min }} caracteres.
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccion.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.direccion.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-email">Email </label>
              <input
                type="email"
                class="form-control"
                name="email"
                id="informacion-pasantia-enail"
                v-model="informacionPasantia.email"
                :class="{
                  'is-invalid': $v.informacionPasantia.email.$error,
                  'is-valid': !$v.informacionPasantia.email.$invalid
                }"
                placeholder="Ingrese el email de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.email.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.email.email">
                La dirección de correo no es válida
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.email.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.email.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <span>Dias a laborar</span>
              <table style="width:100%">
                <tbody>
                  <tr>
                    <td>Lun</td>
                    <td>Mar</td>
                    <td>Mie</td>
                    <td>Jue</td>
                    <td>Vie</td>
                    <td>Sab</td>
                    <td>Dom</td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.lunes"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.martes"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.miercoles"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.jueves"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.viernes"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.sabado"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.domingo"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- --------------------------------------------------------------->
            <div class="form-group">
              <span>Apoyos de la empresa</span>
              <table style="width:100%">
                <tbody>
                  <tr>
                    <td>Bono Alimenticio</td>
                    <td>Apoyo Económico</td>
                    <td>Auxilio de Transporte</td>
                    <td>Capacitación</td>
                  </tr>
                  <tr>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.bonoAlimenticio"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.apoyoEconomico"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.auxilioTransporte"
                      />
                    </td>
                    <td>
                      <input
                        type="checkbox"
                        class="form-control"
                        name="dias"
                        id="informacion-pasantia-lunes"
                        v-model="informacionPasantia.capacitacion"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4">
                      Otro apoyo de la empresa
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4">
                      <input
                        type="text"
                        class="form-control"
                        name="horas-mes"
                        id="informacion-pasantia-horas-mes"
                        v-model="informacionPasantia.otroApoyo"
                        placeholder="Otro tipo de apoyo"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <hr />
            <!-- -------------------------------------------------------------->
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-nombre-empresa">Nombre de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="nombre-empresa"
                id="informacion-pasantia-nombre-empresa"
                v-model="informacionPasantia.nombreEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.nombreEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.nombreEmpresa.$invalid
                }"
                placeholder="Nombre de la empresa donde realizará la pasantía"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.nombreEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.nombreEmpresa.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.nombreEmpresa.$params.minLength.min }} caracteres.
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.nombreEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.nombreEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-nit-empresa">Nit de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="nit-empresa"
                id="informacion-pasantia-nit-empresa"
                v-model="informacionPasantia.nitEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.nitEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.nitEmpresa.$invalid
                }"
                placeholder="Ingrese el Nit de la Empresa sin Guiones ni Puntos"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.nitEmpresa.nitValidator">Ingrese un nit válido, ej: 123123123-1</div>
              
              <div class="text-danger" v-if="!$v.informacionPasantia.nitEmpresa.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.nitEmpresa.$params.minLength.min }} caracteres.
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.nitEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.nitEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-direccion-empresa">Dirección de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="direccion-empresa"
                id="informacion-pasantia-direccion-empresa"
                v-model="informacionPasantia.direccionEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.direccionEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.direccionEmpresa.$invalid
                }"
                placeholder="Direccion de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccionEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccionEmpresa.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.direccionEmpresa.$params.minLength.min }} caracteres.
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.direccionEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.direccionEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

   

  <!--/DEPARTAMENTO//////////////////////////////////////7 ///////////////////7-->
                    <div class="col-md-6 col-12">
                        <div class="form-group"  >
                               <label class="form-control-label "  for="proyecto-facultad">Departamento de la empresa</label> 
                            <b-form-select :options="departamentosEmpresa"  text-field="facultad" value-field="id" id="facultad"
                            v-model="informacionPasantia.departamentoEmpresa" 
                              @input="setMunicipios"                       
                            >
              
                            </b-form-select>
                        
                        </div>
                    </div>
<!--///////////////////////////////////////7 ///////////////////7-->
 <!--       <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-municipio-empresa">Municipio de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="municipio-empresa"
                id="informacion-pasantia-municipio-empresa"
                v-model="informacionPasantia.municipioEmpresa"
                placeholder="Municipio donde está ubicada la empresa"
              />
            </div>
            -->
            <!--/MUNICIPIO//////////////////////////////////////7 ///////////////////7-->
                    <div class="col-md-6 col-12">
                        <div class="form-group"  >
                               <label class="form-control-label "  for="proyecto-facultad">Municipio de la Empresa</label> 
                            <b-form-select :options="municipiosEmpresa"  text-field="facultad" value-field="id" id="facultad"
                            v-model="informacionPasantia.municipioEmpresa"
                            >
                            </b-form-select>
                        
                        </div>
                    </div>
                    <!--///////////////////////////////////////7 ///////////////////7-->
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-telefono-contacto-empresa"
                >Teléfono de contacto de la empresa
              </label>
              <input
                type="text"
                class="form-control"
                name="telefono-contacto-empresa"
                id="informacion-pasantia-telefono-contacto"
                v-model="informacionPasantia.telefonoContactoEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.telefonoContactoEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.telefonoContactoEmpresa.$invalid
                }"
                placeholder="Telefono de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.telefonoContactoEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.telefonoContactoEmpresa.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.telefonoContactoEmpresa.$params.minLength.min }} caracteres.
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.telefonoContactoEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.telefonoContactoEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-email-empresa">Email de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="email-empresa"
                id="informacion-pasantia-email-contacto"
                v-model="informacionPasantia.emailEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.emailEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.emailEmpresa.$invalid
                }"
                placeholder="Correo electrónico de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailEmpresa.email">
                La dirección de correo no es válida
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.emailEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

            <div class="form-group">
              <label class="form-control-label " v-text="$t('ciecytApp.informacionPasantia.sectorEconomicoEmpresa')" for="sector-economico-empresa"
                >Sector Economico de la Empresa</label
              >
              <b-select
                class="form-control"
                name="sector-economico-empresa"
                v-model="informacionPasantia.sectorEconomicoEmpresa"
                id="informacion-pasantia-sector-economico-empresa"
              >
                <option value="Agricola"  informacionPasantia.sectorEconomicoEmpresa="Agricola">Agricola</option>
                <option value="Pecuario" informacionPasantia.sectorEconomicoEmpresa="Pecuario">Pecuario </option>
                <option value="Comercio" informacionPasantia.sectorEconomicoEmpresa="Comercio">Comercio</option>
                <option value="Industria" informacionPasantia.sectorEconomicoEmpresa="Industria">Industria</option>
                <option value="Servicios" informacionPasantia.sectorEconomicoEmpresa="Servicios">Servicios</option>
                <option value="Otro" informacionPasantia.sectorEconomicoEmpresa="Otro">Otro</option>
              </b-select>
            </div>


            <!----------------------------------------------------------------->

            <hr />
       
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-representante-legal">Representante Legal de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="representante-legal-empresa"
                id="informacion-pasantia-representante-legal-empresa"
                v-model="informacionPasantia.representanteLegalEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.representanteLegalEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.representanteLegalEmpresa.$invalid
                }"
                placeholder="Nombres y apellidos del representante legal de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.representanteLegalEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.representanteLegalEmpresa.alphaAndSpaceValidator">
                Este campo solo puede contener letras separadas por espacio en blanco
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.representanteLegalEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.representanteLegalEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>


          
   <!-- <div class="col-md-6 col-12">  -->
   <!--
          <div class="form-group">
              <label class="form-control-label " 
                >Tipo de Documento Representante Legal</label
              >
              <b-select
                class="form-control"
                name="documento-representante-legal-empresa"
              
                id="documento-representante-legal-empresa"
                 @input="setTipoDocumentoRepresentante"
              >
                <option value="Cédula de Ciudadanía" >Cédula de Ciudadanía</option>
                <option value="Cédula de Extranjería" >Cédula de Extranjería </option>
                <option value="Pasaporte">Pasaporte</option>
               
              </b-select>
            </div>
-->
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-identificacion-representante-legal"
                >Identificacion del Representante Legal de la empresa
              </label>
              <input
                type="text"
                class="form-control"
                name="identificacion-representante-legal"
                id="informacion-pasantia-identificacion-representante-legal"
                v-model="informacionPasantia.identificacionRepresentanteLegal"
                :class="{
                  'is-invalid': $v.informacionPasantia.identificacionRepresentanteLegal.$error,
                  'is-valid': !$v.informacionPasantia.identificacionRepresentanteLegal.$invalid
                }"
                placeholder="Ejemplos: C.C.1'210.323.232  C.E.674642 (no escriba espacios)"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.identificacionRepresentanteLegal.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.identificacionRepresentanteLegal.alphaDigitsSpaceQuoteAndDotValidator">
                Este campo solo puede contener letras, digitos, comillas simples, espacios y puntos
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.identificacionRepresentanteLegal.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.identificacionRepresentanteLegal.$params.maxLength.max }} caracteres.
              </div>
            </div>
<!--        </div>    -->
            

           
            <hr />
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-asesor-empresa">Asesor de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="asesor-empresa"
                id="informacion-pasantia-asesor-empresa"
                v-model="informacionPasantia.asesorEmpresa"
                :class="{
                  'is-invalid': $v.informacionPasantia.asesorEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.asesorEmpresa.$invalid
                }"
                placeholder="Nombres y Apellidos del Asesor asignado por parte de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.asesorEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.asesorEmpresa.alphaAndSpaceValidator">
                Este campo solo puede contener letras separadas por espacio en blanco
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.asesorEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.asesorEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>
         
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-cargo-asesor-empresa">Cargo del asesor de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="cargo-asesor-empresa"
                id="informacion-pasantia-cargo-asesor-empresa"
                v-model="informacionPasantia.cargoAsesorEmpresa"
                 :class="{
                  'is-invalid': $v.informacionPasantia.cargoAsesorEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.cargoAsesorEmpresa.$invalid
                }"
                placeholder="Cargo del Asesor en la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.cargoAsesorEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.cargoAsesorEmpresa.alphaAndSpaceValidator">
                Este campo solo puede contener letras separadas por espacio en blanco
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.cargoAsesorEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.cargoAsesorEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>
              
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-email-asesor-empresa">Email del asesor de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="email-asesor-empresa"
                id="informacion-pasantia-email-asesor-empresa"
                v-model="informacionPasantia.emailAsesorEmpresa"
                  :class="{
                  'is-invalid': $v.informacionPasantia.emailAsesorEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.emailAsesorEmpresa.$invalid
                }"
                placeholder="Correo electrónico del Asesor de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailAsesorEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailAsesorEmpresa.email">
                La dirección de correo no es válida
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.emailAsesorEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.emailAsesorEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

             
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-profesion-asesor-empresa"
                >Profesión del asesor de la empresa
              </label>
              <input
                type="text"
                class="form-control"
                name="profesion-asesor-empresa"
                id="informacion-pasantia-profesion-asesor-empresa"
                v-model="informacionPasantia.profesionAsesorEmpresa"
                  :class="{
                  'is-invalid': $v.informacionPasantia.profesionAsesorEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.profesionAsesorEmpresa.$invalid
                }"
                placeholder="Profesión del Asesor de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.profesionAsesorEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.profesionAsesorEmpresa.alphaAndSpaceValidator">
                Este campo solo puede contener letras separadas por espacio en blanco
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.profesionAsesorEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.profesionAsesorEmpresa.$params.maxLength.max }} caracteres.
              </div>
            </div>

           
            <div class="form-group">
              <label class="form-control-label " for="informacion-pasantia-celular-asesor-empresa">Celular del asesor de la empresa </label>
              <input
                type="text"
                class="form-control"
                name="celular-asesor-empresa"
                id="informacion-pasantia-celular-asesor-empresa"
                v-model="informacionPasantia.celularAsesorEmpresa"
                  :class="{
                  'is-invalid': $v.informacionPasantia.celularAsesorEmpresa.$error,
                  'is-valid': !$v.informacionPasantia.celularAsesorEmpresa.$invalid
                }"
                placeholder="Número de celular del Asesor de la empresa"
              />
              <div class="valid-feedback"></div>
              <div class="text-danger" v-if="!$v.informacionPasantia.celularAsesorEmpresa.required">Este campo es requerido</div>
              <div class="text-danger" v-if="!$v.informacionPasantia.celularAsesorEmpresa.digitsLineasValidator">
                Este campo solo puede digitos y separador -
              </div>
              <div class="text-danger" v-if="!$v.informacionPasantia.celularAsesorEmpresa.maxLength">
                Este campo no debe tener más de {{ $v.informacionPasantia.celularAsesorEmpresa.$params.maxLength.max }} caracteres.
              </div>
               <div class="text-danger" v-if="!$v.informacionPasantia.celularAsesorEmpresa.minLength">
                Este campo debe tener al menos {{ $v.informacionPasantia.celularAsesorEmpresa.$params.minLength.max }} caracteres.
              </div>
            </div>
          
          </div>
        </div>

        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>

          <button type="submit" id="save-entity" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Inject, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

import MenuLateralPasantia from '@/components/propuesta_pasantia/menu_lateral_pasantia.vue';
import InformacionPasantiaService from '@/entities/informacion-pasantia/informacion-pasantia.service';
import { IInformacionPasantia, InformacionPasantia } from '@/shared/model/informacion-pasantia.model';

//import { IProyecto, Proyecto } from '@/shared/model/proyecto.model';
//import ProyectoService from '@/entities/proyecto/proyecto.service';

import { numeric, required, minLength, maxLength, between, url, email, alpha, helpers } from 'vuelidate/lib/validators';
const alphaAndSpaceValidator = helpers.regex('alphaAndSpace', /^[A-Za-z\u00C0-\u017F- ]+$/i);
const digitsQuoteAndDotValidator = helpers.regex('digitsQuoteAndDot', /^[0-9\/.//'/]+$/i);
const alphaDigitsSpaceQuoteAndDotValidator = helpers.regex('alphaDigitsSpaceQuoteAndDot', /^[A-Za-z0-9 \/.//'/]+$/i);
const digitsLineasValidator = helpers.regex('digitsLineas', /^[0-9 \u002D]+$/i);
const nitValidator = helpers.regex('nit', /(^[0-9]+-{1}[0-9]{1})/);


import { id } from 'date-fns/esm/locale';

//import { id } from 'date-fns/locale';

const validations: any = {
  
  informacionPasantia: {
    duracionHoras: { required, between: between(480, 880) },
    direccion: { required, maxLength: maxLength(120), minLength: minLength(3) },
    email: { required, email, maxLength: maxLength(254) },
    convenio: { required, between: between(20, 100000000) }, //convenio
    nombreEmpresa: { required, maxLength: maxLength(100), minLength: minLength(3) },
    nitEmpresa: { maxLength: maxLength(15), minLength: minLength(9), nitValidator },
    direccionEmpresa: { required, maxLength: maxLength(100), minLength: minLength(3) },
    telefonoContactoEmpresa: { required, maxLength: maxLength(15), minLength: minLength(7) },
    emailEmpresa: { required, email, maxLength: maxLength(254) },
    representanteLegalEmpresa: { required, maxLength: maxLength(100), alphaAndSpaceValidator },
    identificacionRepresentanteLegal : { required, maxLength: maxLength(20), alphaDigitsSpaceQuoteAndDotValidator },
    asesorEmpresa:  { required, maxLength: maxLength(100), alphaAndSpaceValidator }, 
    cargoAsesorEmpresa:  { required, maxLength: maxLength(100), alphaAndSpaceValidator },
    emailAsesorEmpresa: { required, email, maxLength: maxLength(254) },
    profesionAsesorEmpresa:  { required, maxLength: maxLength(100), alphaAndSpaceValidator },
    celularAsesorEmpresa:  { required, maxLength: maxLength(20), minLength: minLength(10), digitsLineasValidator },
  }
};

@Component({
  components: { MenuLateralPasantia },

  validations
})
export default class PasantiaInformacionEmpresa extends Vue {
  //  @Inject('proyectoService') private proyectoService: () => ProyectoService;
  @Inject('informacionPasantiaService') private informacionPasantiaService: () => InformacionPasantiaService;

  @Inject('alertService') private alertService: () => AlertService;

  public informacionPasantia: IInformacionPasantia = new InformacionPasantia();
  
  

  // public proyecto: IProyecto = new Proyecto();
  //public proyId: string = null;
  public proyId: any;

  public isSaving = false;

  public submitStatus: string = 'PENDING';
  public iniciandoDuracionHoras: boolean = true;
  public iniciandoDireccion: boolean = true;
  
  public departamentosMunicipios: any;
  public departamentosEmpresa = [];
  public municipiosEmpresa = [];

  async mounted() {
    this.proyId = this.$route.params.proyectoId;
    await this.retrieveInformacionPasantia();
    this.readJson("../content/json/xdk5-pm3f.json");
 
   
  }

 readJson(filePath) {
        var request = new XMLHttpRequest();
        request.open("GET",filePath, false);
        request.send(null);
        this.departamentosMunicipios = JSON.parse(request.responseText);
        this.departamentosMunicipios.forEach(element => {
        if (this.departamentosEmpresa.some(e => e == element.departamento)) return;
           this.departamentosEmpresa.push(element.departamento); 
       });
       this.departamentosEmpresa.sort();
       //console.log (this.departamentos); 
    }

  retrieveInformacionPasantia() {
    this.informacionPasantiaService()
      .findInformacionPasantiaProyecto(this.proyId)
      .then(res => {
        if (res) this.informacionPasantia = res;
      });
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.proyectoId) {
        this.proyId = this.$route.params.proyectoId;
        // vm.retrieveProyecto(to.params.proyectoId);
        //vm.retrieveAllInformacionPasantia();
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;

    this.$v.$touch();
    if (this.$v.$invalid) {
      //  this.submitStatus = 'ERROR';
       console.log(this.$v);
        // console.log(this.sectoresEconomicos);
    } else {
       //this.informacionPasantia.identificacionRepresentanteLegal=this.tipoDocumentoRepresentante + ":" + this.numeroDocumentoRepresentante
      if (this.informacionPasantia.id) {
        this.informacionPasantiaService()
          .update(this.informacionPasantia)
          .then(param => {
            this.isSaving = false;
            this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyId } });
            const message = this.$t('ciecytApp.proyecto.updated', { param: param.id });
            this.alertService().showAlert(message, 'info');
          });
      } else {
        //console.log(this.proyId);
        //this.informacionPasantia.informacionPasantiaProyectoId
        this.informacionPasantia.informacionPasantiaProyectoId = parseInt(this.proyId);
        //console.log(this.informacionPasantia);

        this.informacionPasantiaService()
          .create(this.informacionPasantia)
          .then(param => {
            this.isSaving = false;

            //this.proyId = String(param.id);

            this.$router.push({ name: 'PropuestaPasantiaInformacionEmpresaView', params: { proyectoId: this.proyId } });

            const message = 'Se ha creado un nuevo elemento de pasantia';
            this.alertService().showAlert(message, 'success');
          });
      }
      this.submitStatus = 'PENDING';
      setTimeout(() => {
        this.submitStatus = 'OK';
      }, 500);
    }
  }
  //recupera un proyecto
  /*
        retrieveProyecto() {
             //console.log(this.proyId);
             this.proyectoService().find(this.proyId).then((res) => {
                this.proyecto = res;
            });
        }*/

  initRelationships() {
    //this.proyId = this.$route.params.proyectoId;
    //console.log(this.proyId);
   
   
  }
     setMunicipios(value){
            this.municipiosEmpresa = [];
            let munic =  this.departamentosMunicipios.filter(function(e) {
                return (e.departamento == value);
            });
            munic.forEach(element => {
                this.municipiosEmpresa.push(element.municipio);
            });
            this.municipiosEmpresa.sort();
        }
}
</script>

<style scoped>
</style>