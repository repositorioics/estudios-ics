/*Plantilla*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'heading', 'Cohorte Influenza, Dengue','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'title', 'Estudios de Investigaci�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'footer', 'Instituto de Ciencias Sostenibles','0','0',0);

/*login page*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login', 'Ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountExpired', 'Cuenta de usuario ha expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountLocked', 'Cuenta de usuario est� bloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountNotLocked', 'Cuenta de usuario est� desbloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.badCredentials', 'Nombre de usuario o contraseña incorrectos!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.credentialsExpired', 'Credenciales de usuario han expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.maxSessionsOut', 'Tiene una sesi�n activa! No puede crear otra!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.message', 'Por favor ingresar su nombre de usuario y contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.username', 'Nombre de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.password', 'Contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.forgot.password', 'Olvid� contraseña?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userEnabled', 'Usuario esta activo!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userDisabled', 'Usuario esta inactivo!','0','0',0);


/*Menu*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'home', 'Inicio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dashboard', 'Panel de control','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'admin', 'Administraci�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'users', 'Usuarios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'chfcasos', 'Monitoreo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'chfcasossearch', 'Buscar Casa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'superv', 'Supervisi�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'intensiveMonitoring', 'Monitoreo Intensivo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'versionLetters', 'Versiones Cartas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'laboratory', 'Laboratorio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'print.codes', 'Impresi�n C�digos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logout', 'Salir','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'reports', 'Reportes','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'report.file.data', 'Datos Generales','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'uo1.positives', 'Positivos UO1','0','0',0);


/*Catalogos*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si No CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SINO_SI', '1', 'CHF_CAT_SINO', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SINO_NO', '0', 'CHF_CAT_SINO', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SEXO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Sexo CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SEXO_M', 'M', 'CHF_CAT_SEXO', NULL, '0', 1, '0', 'Masculino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SEXO_F', 'F', 'CHF_CAT_SEXO', NULL, '0', 2, '0', 'Femenino');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DF', NULL, NULL, NULL, '1', 0, '0', 'Catalogo ubicacion dentro o fuera de casa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DF_01', '1', 'CHF_CAT_DF', NULL, '0', 1, '0', 'Fuera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DF_02', '2', 'CHF_CAT_DF', NULL, '0', 2, '0', 'Dentro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DF_03', '3', 'CHF_CAT_DF', NULL, '0', 3, '0', 'Dentro y Fuera');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_COMPARTIDO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo llave agua compartida CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_COMPARTIDO_C', '1', 'CHF_CAT_COMPARTIDO', NULL, '0', 1, '0', 'Compartido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_COMPARTIDO_N', '2', 'CHF_CAT_COMPARTIDO', NULL, '0', 2, '0', 'No compartido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de paredes casa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_01', '1', 'CHF_CAT_MAT_PARED', NULL, '0', 1, '0', 'Madera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_02', '2', 'CHF_CAT_MAT_PARED', NULL, '0', 2, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_03', '3', 'CHF_CAT_MAT_PARED', NULL, '0', 3, '0', 'Pl�stico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_04', '4', 'CHF_CAT_MAT_PARED', NULL, '0', 4, '0', 'Cart�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_05', '5', 'CHF_CAT_MAT_PARED', NULL, '0', 5, '0', 'Adobe');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_06', '6', 'CHF_CAT_MAT_PARED', NULL, '0', 6, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PARED_998', '998', 'CHF_CAT_MAT_PARED', NULL, '0', 7, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de piso casa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO_01', '1', 'CHF_CAT_MAT_PISO', NULL, '0', 1, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO_02', '2', 'CHF_CAT_MAT_PISO', NULL, '0', 2, '0', 'Ladrillos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO_03', '3', 'CHF_CAT_MAT_PISO', NULL, '0', 3, '0', 'Piso de tierra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO_04', '4', 'CHF_CAT_MAT_PISO', NULL, '0', 4, '0', 'Cer�mica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_PISO_998', '998', 'CHF_CAT_MAT_PISO', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_TECHO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de techo casa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_TECHO_01', '1', 'CHF_CAT_MAT_TECHO', NULL, '0', 1, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_TECHO_02', '2', 'CHF_CAT_MAT_TECHO', NULL, '0', 2, '0', 'Pl�stico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_TECHO_03', '3', 'CHF_CAT_MAT_TECHO', NULL, '0', 3, '0', 'Tejas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MAT_TECHO_998', '998', 'CHF_CAT_MAT_TECHO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FUN_ABANICO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de funcionamiento aire acondicionado CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FUN_ABANICO_D', '1', 'CHF_CAT_FUN_ABANICO', NULL, '0', 1, '0', 'D�a');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FUN_ABANICO_N', '2', 'CHF_CAT_FUN_ABANICO', NULL, '0', 2, '0', 'Noche');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_COCINA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_COCINA_D', '1', 'CHF_CAT_FREC_COCINA', NULL, '0', 1, '0', 'Diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_COCINA_S', '2', 'CHF_CAT_FREC_COCINA', NULL, '0', 2, '0', 'Semanal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_COCINA_Q', '3', 'CHF_CAT_FREC_COCINA', NULL, '0', 3, '0', 'Quincenal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_COCINA_M', '4', 'CHF_CAT_FREC_COCINA', NULL, '0', 4, '0', 'Mensual');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visita no exitosa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV_01', '1', 'CHF_CAT_NV', NULL, '0', 1, '0', 'No se ubica el domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV_02', '2', 'CHF_CAT_NV', NULL, '0', 2, '0', 'Casa cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV_03', '3', 'CHF_CAT_NV', NULL, '0', 3, '0', 'Cambio domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV_04', '4', 'CHF_CAT_NV', NULL, '0', 4, '0', 'No est� jefe casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NV_998', '998', 'CHF_CAT_NV', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no participa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_01', '1', 'CHF_CAT_NPT', NULL, '0', 1, '0', 'No desea que su casa participe en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_02', '2', 'CHF_CAT_NPT', NULL, '0', 2, '0', 'Cambiar� de Domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_03', '3', 'CHF_CAT_NPT', NULL, '0', 3, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_04', '4', 'CHF_CAT_NPT', NULL, '0', 4, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_998', '998', 'CHF_CAT_NPT', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta tamizaje persona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP_01', '1', 'CHF_CAT_NPP', NULL, '0', 1, '0', 'No desea participar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP_02', '2', 'CHF_CAT_NPP', NULL, '0', 2, '0', 'Cambiar� de Domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP_03', '3', 'CHF_CAT_NPP', NULL, '0', 3, '0', 'Tiene seguro m�dico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP_04', '4', 'CHF_CAT_NPP', NULL, '0', 4, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPT_05', '5', 'CHF_CAT_NPP', NULL, '0', 5, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NPP_998', '998', 'CHF_CAT_NPP', NULL, '0', 6, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo criterios de inclusion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CI_01', '1', 'CHF_CAT_CI', NULL, '0', 1, '0', '1) Vivir en �rea de cobertura del CSSFV.');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CI_02', '2', 'CHF_CAT_CI', NULL, '0', 2, '0', '2) Vivir en un hogar con al menos 1 niño/a menor de 12 años.');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CI_03', '3', 'CHF_CAT_CI', NULL, '0', 3, '0', '3) Tener la intenci�n de permanecer en el �rea durante el estudio.');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CI_04', '4', 'CHF_CAT_CI', NULL, '0', 4, '0', '4) Tener una tarjeta de vacunaci�n (niños/as) o un documento de identificaci�n (adultos) para confirmar la edad y la residencia');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde asiste por enfermedades');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_01', '1', 'CHF_CAT_DONDEASISTE', NULL, '0', 1, '0', 'Centro de Salud S�crates Flores');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_02', '2', 'CHF_CAT_DONDEASISTE', NULL, '0', 2, '0', 'Otro centro de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_03', '3', 'CHF_CAT_DONDEASISTE', NULL, '0', 3, '0', 'Puesto de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_04', '4', 'CHF_CAT_DONDEASISTE', NULL, '0', 4, '0', 'Hospital P�blico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_05', '5', 'CHF_CAT_DONDEASISTE', NULL, '0', 5, '0', 'M�dico Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_06', '6', 'CHF_CAT_DONDEASISTE', NULL, '0', 6, '0', 'Seguro Social');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_07', '7', 'CHF_CAT_DONDEASISTE', NULL, '0', 7, '0', 'Curandero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_08', '8', 'CHF_CAT_DONDEASISTE', NULL, '0', 8, '0', 'Farmacia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DONDEASISTE_998', '998', 'CHF_CAT_DONDEASISTE', NULL, '0', 9, '0', 'Otro');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PUESTO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo puesto de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PUESTO_01', '1', 'CHF_CAT_PUESTO', NULL, '0', 1, '0', 'P/S Cristo del Rosario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PUESTO_02', '2', 'CHF_CAT_PUESTO', NULL, '0', 2, '0', 'P/S San Sebastian');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo relacion familiar tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_01', '1', 'CHF_CAT_RFTUTOR', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_02', '2', 'CHF_CAT_RFTUTOR', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_03', '3', 'CHF_CAT_RFTUTOR', NULL, '0', 3, '0', 'Abuelo (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_04', '4', 'CHF_CAT_RFTUTOR', NULL, '0', 4, '0', 'T�o (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_05', '5', 'CHF_CAT_RFTUTOR', NULL, '0', 5, '0', 'Hermano (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RFTUTOR_998', '998', 'CHF_CAT_RFTUTOR', NULL, '0', 6, '0', 'Otra relaci�n familiar');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_PARTO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de parto CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_PARTO_01', '1', 'CHF_CAT_TIPO_PARTO', NULL, '0', 1, '0', 'Vaginal / Natural');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_PARTO_02', '2', 'CHF_CAT_TIPO_PARTO', NULL, '0', 2, '0', 'Ces�rea');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_PARTO_03', '3', 'CHF_CAT_TIPO_PARTO', NULL, '0', 4, '0', 'Rehusa contestar esta pregunta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_PARTO_999', '999', 'CHF_CAT_TIPO_PARTO', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de documentos m�dicos CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO_01', '1', 'CHF_CAT_DOC_MEDICO', NULL, '0', 1, '0', 'Control Prenatal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO_02', '2', 'CHF_CAT_DOC_MEDICO', NULL, '0', 2, '0', 'Tarjeta de Vacuna');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO_03', '3', 'CHF_CAT_DOC_MEDICO', NULL, '0', 3, '0', 'Epicrisis');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO_04', '4', 'CHF_CAT_DOC_MEDICO', NULL, '0', 4, '0', 'Expediente Cl�nico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_DOC_MEDICO_998', '998', 'CHF_CAT_DOC_MEDICO', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SNDR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido/Rehusa CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SNDR_01', '1', 'CHF_CAT_SNDR', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SNDR_02', '0', 'CHF_CAT_SNDR', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SNDR_03', '2', 'CHF_CAT_SNDR', NULL, '0', 4, '0', 'Rehusa contestar esta pregunta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SNDR_999', '999', 'CHF_CAT_SNDR', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SND', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SND_01', '1', 'CHF_CAT_SND', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SND_02', '0', 'CHF_CAT_SND', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_SND_999', '9', 'CHF_CAT_SND', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo niveles de educaci�n CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU_01', '1', 'CHF_CAT_NIV_EDU', NULL, '0', 1, '0', 'Ninguno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU_02', '2', 'CHF_CAT_NIV_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU_03', '3', 'CHF_CAT_NIV_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU_04', '4', 'CHF_CAT_NIV_EDU', NULL, '0', 4, '0', 'T�cnico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_NIV_EDU_05', '5', 'CHF_CAT_NIV_EDU', NULL, '0', 5, '0', 'Universitario');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TRABAJO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de trabajo CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TRABAJO_01', '1', 'CHF_CAT_TIP_TRABAJO', NULL, '0', 1, '0', 'Trabajo Formal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TRABAJO_02', '2', 'CHF_CAT_TIP_TRABAJO', NULL, '0', 2, '0', 'Trabajo Informal');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_GRD_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo grados escolares CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_GRD_EDU_01', '1', 'CHF_CAT_GRD_EDU', NULL, '0', 1, '0', 'Preescolar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_GRD_EDU_02', '2', 'CHF_CAT_GRD_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_GRD_EDU_03', '3', 'CHF_CAT_GRD_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_GRD_EDU_04', '4', 'CHF_CAT_GRD_EDU', NULL, '0', 4, '0', 'Universidad');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TURNO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo turnos de estudio CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TURNO_01', '1', 'CHF_CAT_TURNO', NULL, '0', 1, '0', 'AM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TURNO_02', '2', 'CHF_CAT_TURNO', NULL, '0', 2, '0', 'PM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TURNO_03', '3', 'CHF_CAT_TURNO', NULL, '0', 3, '0', 'SAB');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TURNO_04', '4', 'CHF_CAT_TURNO', NULL, '0', 4, '0', 'DOM');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CUIDAN_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo d�nde cuidad al niño si no va a la escuela CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CUIDAN_NINO_01', '1', 'CHF_CAT_CUIDAN_NINO', NULL, '0', 1, '0', 'CDI');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CUIDAN_NINO_02', '2', 'CHF_CAT_CUIDAN_NINO', NULL, '0', 2, '0', 'Vecino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CUIDAN_NINO_03', '3', 'CHF_CAT_CUIDAN_NINO', NULL, '0', 3, '0', 'En su casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_CUIDAN_NINO_998', '998', 'CHF_CAT_CUIDAN_NINO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIVE_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo con qui�n vive el niño CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIVE_NINO_01', '1', 'CHF_CAT_VIVE_NINO', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIVE_NINO_02', '2', 'CHF_CAT_VIVE_NINO', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIVE_NINO_03', '3', 'CHF_CAT_VIVE_NINO', NULL, '0', 3, '0', 'Ambos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIVE_NINO_998', '998', 'CHF_CAT_VIVE_NINO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA_01', '1', 'CHF_CAT_FREC_FUMA', NULL, '0', 1, '0', 'Diariamente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA_02', '2', 'CHF_CAT_FREC_FUMA', NULL, '0', 2, '0', 'No diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA_03', '3', 'CHF_CAT_FREC_FUMA', NULL, '0', 3, '0', 'Semanalmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA_04', '4', 'CHF_CAT_FREC_FUMA', NULL, '0', 4, '0', 'Mensualmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FREC_FUMA_05', '5', 'CHF_CAT_FREC_FUMA', NULL, '0', 5, '0', 'De vez en cuando');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES', NULL, NULL, NULL, '1', 0, '0', 'Catalogo meses del año CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_01', '01', 'CHF_CAT_MESES', NULL, '0', 1, '0', 'Enero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_02', '02', 'CHF_CAT_MESES', NULL, '0', 2, '0', 'Febrero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_03', '03', 'CHF_CAT_MESES', NULL, '0', 3, '0', 'Marzo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_04', '04', 'CHF_CAT_MESES', NULL, '0', 4, '0', 'Abril');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_05', '05', 'CHF_CAT_MESES', NULL, '0', 5, '0', 'Mayo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_06', '06', 'CHF_CAT_MESES', NULL, '0', 6, '0', 'Junio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_07', '07', 'CHF_CAT_MESES', NULL, '0', 7, '0', 'Julio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_08', '08', 'CHF_CAT_MESES', NULL, '0', 8, '0', 'Agosto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_09', '09', 'CHF_CAT_MESES', NULL, '0', 9, '0', 'Septiembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_10', '10', 'CHF_CAT_MESES', NULL, '0', 10, '0', 'Octubre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_11', '11', 'CHF_CAT_MESES', NULL, '0', 11, '0', 'Noviembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_MESES_12', '12', 'CHF_CAT_MESES', NULL, '0', 12, '0', 'Diciembre');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_EXCLU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo almentacion exclusiva CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_EXCLU_01', '1', 'CHF_CAT_EXCLU', NULL, '0', 1, '0', 'Exclusivamente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_EXCLU_02', '2', 'CHF_CAT_EXCLU', NULL, '0', 2, '0', 'No Exclusivamente');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPECHO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo dio el pecho CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPECHO_01', '1', 'CHF_CAT_TIEMPECHO', NULL, '0', 1, '0', 'Menos de un mes o un mes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPECHO_02', '2', 'CHF_CAT_TIEMPECHO', NULL, '0', 2, '0', 'Numero de meses (02- 36)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPECHO_03', '3', 'CHF_CAT_TIEMPECHO', NULL, '0', 3, '0', 'Aun se lo da');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPECHO_999', '999', 'CHF_CAT_TIEMPECHO', NULL, '0', 4, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FORM_ALIM', NULL, NULL, NULL, '1', 0, '0', 'Catalogo forma de alimentar nino CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FORM_ALIM_01', '1', 'CHF_CAT_FORM_ALIM', NULL, '0', 1, '0', 'Lo alimenta con m�s leche materna que otro tipo de leche u otros alimentos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FORM_ALIM_02', '2', 'CHF_CAT_FORM_ALIM', NULL, '0', 2, '0', 'Lo alimenta con m�s alimentos u otros tipos de leche, que leche materna');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_FORM_ALIM_03', '3', 'CHF_CAT_FORM_ALIM', NULL, '0', 3, '0', 'Lo alimenta igual con leche materna u otro tipo de leche o alimentos');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_EXC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo le dio el pecho para alimentar exclusivamente CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_EXC_01', '1', 'CHF_CAT_ALIM_EXC', NULL, '0', 1, '0', 'Un mes o menos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_EXC_02', '2', 'CHF_CAT_ALIM_EXC', NULL, '0', 2, '0', 'Numero de meses (02- 36)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_EXC_999', '999', 'CHF_CAT_ALIM_EXC', NULL, '0', 4, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OTRA_ALIM', NULL, NULL, NULL, '1', 0, '0', 'Catalogo otro tipo de alimentaci�n le da o le dio a su bebe CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OTRA_ALIM_01', '1', 'CHF_CAT_OTRA_ALIM', NULL, '0', 1, '0', 'Leche distinto a la leche de pecho (incluyendo formula, leche en polvo, leche liquida)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OTRA_ALIM_02', '2', 'CHF_CAT_OTRA_ALIM', NULL, '0', 2, '0', 'L�quidos distinto a leche (incluyendo aguas, refrescos, juegos, te)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OTRA_ALIM_03', '3', 'CHF_CAT_OTRA_ALIM', NULL, '0', 3, '0', '	Alimentos s�lidos (atoles, pures, cereales, caldos con frijoles, arroz, sopa o verduras o cualquier otro alimento)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DLP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo edad comenz� a darle l�quidos distintos a la leche de pecho CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DLP_01', '1', 'CHF_CAT_ALIM_DLP', NULL, '0', 1, '0', 'Meses (0-24)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DLP_02', '2', 'CHF_CAT_ALIM_DLP', NULL, '0', 2, '0', 'Aun no le da dado ning�n l�quido a parte de la leche de pecho');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DLP_999', '999', 'CHF_CAT_ALIM_DLP', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo edad comenz� a darle l�quidos distintos a la leche CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DL_01', '1', 'CHF_CAT_ALIM_DL', NULL, '0', 1, '0', 'Meses (0-36)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DL_02', '2', 'CHF_CAT_ALIM_DL', NULL, '0', 2, '0', 'Aun no le da dado ning�n l�quido a parte de la leche de pecho');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_DL_999', '999', 'CHF_CAT_ALIM_DL', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_SOL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo edad comenz� a darle alimentos s�lidos CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_SOL_01', '1', 'CHF_CAT_ALIM_SOL', NULL, '0', 1, '0', 'Meses (0-36)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_SOL_02', '2', 'CHF_CAT_ALIM_SOL', NULL, '0', 2, '0', 'Aun no le da alimentos s�lidos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_ALIM_SOL_999', '999', 'CHF_CAT_ALIM_SOL', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PROP_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo prop�sito toma de muestra CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PROP_MX_01', '1', 'CHF_CAT_PROP_MX', NULL, '0', 1, '0', 'Muestreo anual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PROP_MX_02', '2', 'CHF_CAT_PROP_MX', NULL, '0', 2, '0', 'Muestra enfermo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PROP_MX_03', '3', 'CHF_CAT_PROP_MX', NULL, '0', 3, '0', 'Muestra transmisi�n');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de muestra CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_01', '1', 'CHF_CAT_TIP0_MX', NULL, '0', 1, '0', 'Sangre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_02', '2', 'CHF_CAT_TIP0_MX', NULL, '0', 2, '0', 'Hisopado Faringeo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_03', '3', 'CHF_CAT_TIP0_MX', NULL, '0', 3, '0', 'Hisopado Nasal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_04', '4', 'CHF_CAT_TIP0_MX', NULL, '0', 4, '0', 'Hisopado Nasal y Faringeo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_05', '5', 'CHF_CAT_TIP0_MX', NULL, '0', 5, '0', 'Lavado Nasal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_09', '9', 'CHF_CAT_TIP0_MX', NULL, '0', 6, '0', 'Sin muestra');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de tubos muestras CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_01', '1', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 1, '0', 'Rojo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_02', '2', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 2, '0', 'BHC');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_03', '3', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 3, '0', 'PBMC');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_04', '4', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 4, '0', 'Medio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_05', '5', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 5, '0', 'MEM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_TUBO_MX_09', '9', 'CHF_CAT_TIP_TUBO_MX', NULL, '0', 6, '0', 'SM');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_RESP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de muestra CHF Resp');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_RESP_02', '2', 'CHF_CAT_TIP0_MX_RESP', NULL, '0', 1, '0', 'Hisopado Faringeo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_RESP_03', '3', 'CHF_CAT_TIP0_MX_RESP', NULL, '0', 2, '0', 'Hisopado Nasal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_RESP_04', '4', 'CHF_CAT_TIP0_MX_RESP', NULL, '0', 3, '0', 'Hisopado Nasal y Faringeo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP0_MX_RESP_05', '5', 'CHF_CAT_TIP0_MX_RESP', NULL, '0', 4, '0', 'Lavado Nasal');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo raz�n no se toma muestra CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_01', '1', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 1, '0', 'Muestra dif�cil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_02', '2', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 2, '0', 'Se descanaliz�');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_03', '3', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 3, '0', 'Se pinch� mas de 2 veces');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_04', '4', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 4, '0', 'Padre/niño no acept� tomar muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_05', '5', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 5, '0', 'Padre o tutor despu�s de pinchadazo no desea que se le tome mx al niño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_998', '998', 'CHF_CAT_RAZON_NO_MX', NULL, '0', 6, '0', 'Otra razon');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_RESP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo raz�n no se toma muestra respiratoria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_RESP_01', '1', 'CHF_CAT_RAZON_NO_MX_RESP', NULL, '0', 1, '0', 'Muestra dif�cil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_RESP_04', '4', 'CHF_CAT_RAZON_NO_MX_RESP', NULL, '0', 2, '0', 'Padre/niño no acept� tomar muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_RAZON_NO_MX_RESP_998', '998', 'CHF_CAT_RAZON_NO_MX_RESP', NULL, '0', 3, '0', 'Otra razon');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OBSERV_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo observaciones toma muestra CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OBSERV_MX_01', '1', 'CHF_CAT_OBSERV_MX', NULL, '0', 1, '0', 'Muestra dif�cil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OBSERV_MX_02', '2', 'CHF_CAT_OBSERV_MX', NULL, '0', 2, '0', 'Ninguna');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_OBSERV_MX_998', '998', 'CHF_CAT_OBSERV_MX', NULL, '0', 3, '0', 'Otra razon');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de muestra CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX_01', '0', 'CHF_CAT_PINCH_MX', NULL, '0', 1, '0', '0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX_02', '1', 'CHF_CAT_PINCH_MX', NULL, '0', 2, '0', '1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX_03', '2', 'CHF_CAT_PINCH_MX', NULL, '0', 3, '0', '2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX_04', '3', 'CHF_CAT_PINCH_MX', NULL, '0', 4, '0', '3');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_PINCH_MX_05', '4', 'CHF_CAT_PINCH_MX', NULL, '0', 5, '0', '4');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de ambientes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA_01', 'banio', 'CHF_CAT_TIPO_AREA', NULL, '0', 1, '0', 'Baño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA_02', 'cocina', 'CHF_CAT_TIPO_AREA', NULL, '0', 2, '0', 'Cocina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA_03', 'comedor', 'CHF_CAT_TIPO_AREA', NULL, '0', 3, '0', 'Comedor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA_04', 'habitacion', 'CHF_CAT_TIPO_AREA', NULL, '0', 4, '0', 'Habitaci�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_AREA_05', 'sala', 'CHF_CAT_TIPO_AREA', NULL, '0', 5, '0', 'Sala');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_FUMIGA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia fumigaci�n Seroprevalencia Arbovirus (SA)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_FUMIGA_01', '1', 'SA_CAT_FUMIGA', NULL, '0', 1, '0', 'Semanal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_FUMIGA_02', '2', 'SA_CAT_FUMIGA', NULL, '0', 2, '0', 'Mensual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_FUMIGA_03', '3', 'SA_CAT_FUMIGA', NULL, '0', 3, '0', 'Cada 6 Meses');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_FUMIGA_04', '4', 'SA_CAT_FUMIGA', NULL, '0', 4, '0', 'Una Vez al Año');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM', NULL, NULL, NULL, '1', 0, '0', 'Catalogo realaci�n familiar SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM_01', '1', 'SA_CAT_RELFAM', NULL, '0', 1, '0', 'Pareja');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM_02', '2', 'SA_CAT_RELFAM', NULL, '0', 2, '0', 'Hijo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM_03', '3', 'SA_CAT_RELFAM', NULL, '0', 3, '0', 'Padres');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM_04', '4', 'SA_CAT_RELFAM', NULL, '0', 4, '0', 'Amigo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_RELFAM_998', '998', 'SA_CAT_RELFAM', NULL, '0', 5, '0', 'Otra relacion familiar');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo definici�n zika SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_01', '1', 'SA_CAT_DEF_ZIKA', NULL, '0', 1, '0', 'Virus');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_02', '2', 'SA_CAT_DEF_ZIKA', NULL, '0', 2, '0', 'Bacteria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_03', '3', 'SA_CAT_DEF_ZIKA', NULL, '0', 3, '0', 'Una enfermedad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_04', '4', 'SA_CAT_DEF_ZIKA', NULL, '0', 4, '0', 'Mosquito');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_05', '5', 'SA_CAT_DEF_ZIKA', NULL, '0', 5, '0', 'Fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_DEF_ZIKA_998', '998', 'SA_CAT_DEF_ZIKA', NULL, '0', 6, '0', 'Otra');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo transmisi�n de zika SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA_01', '1', 'SA_CAT_TRA_ZIKA', NULL, '0', 1, '0', 'Mosquito');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA_02', '2', 'SA_CAT_TRA_ZIKA', NULL, '0', 2, '0', 'Sexual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA_03', '3', 'SA_CAT_TRA_ZIKA', NULL, '0', 3, '0', 'Besos/Saliva');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA_04', '4', 'SA_CAT_TRA_ZIKA', NULL, '0', 4, '0', 'Contacto de piel');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_TRA_ZIKA_998', '998', 'SA_CAT_TRA_ZIKA', NULL, '0', 5, '0', 'Otra');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo s�ntomas SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_01', '1', 'SA_CAT_SINTOMAS', NULL, '0', 1, '0', 'Rash');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_02', '2', 'SA_CAT_SINTOMAS', NULL, '0', 2, '0', 'Fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_03', '3', 'SA_CAT_SINTOMAS', NULL, '0', 3, '0', 'Ojos rojos (conjunctivitis)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_04', '4', 'SA_CAT_SINTOMAS', NULL, '0', 4, '0', 'Dolor de hueso (arthralgia)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_05', '5', 'SA_CAT_SINTOMAS', NULL, '0', 5, '0', 'Dolor de m�sculos( myalgia)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_06', '6', 'SA_CAT_SINTOMAS', NULL, '0', 6, '0', 'Inflamaci�n  (peri-articular edema)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINTOMAS_996', '996', 'SA_CAT_SINTOMAS', NULL, '0', 7, '0', 'Ninguno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo s�ntomas de zika SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_01', '1', 'SA_CAT_SINT_ZIKA', NULL, '0', 1, '0', 'Rash');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_02', '2', 'SA_CAT_SINT_ZIKA', NULL, '0', 2, '0', 'Fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_03', '3', 'SA_CAT_SINT_ZIKA', NULL, '0', 3, '0', 'Ojos rojos (conjunctivitis)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_04', '4', 'SA_CAT_SINT_ZIKA', NULL, '0', 4, '0', 'Dolor de hueso (arthralgia)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_05', '5', 'SA_CAT_SINT_ZIKA', NULL, '0', 5, '0', 'Dolor de m�sculos( myalgia)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_06', '6', 'SA_CAT_SINT_ZIKA', NULL, '0', 6, '0', 'Inflamaci�n  (peri-articular edema)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_07', '7', 'SA_CAT_SINT_ZIKA', NULL, '0', 7, '0', 'Diarrea');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_08', '8', 'SA_CAT_SINT_ZIKA', NULL, '0', 8, '0', 'Dolor de cabeza');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_09', '9', 'SA_CAT_SINT_ZIKA', NULL, '0', 9, '0', 'Dolor de est�mago');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_10', '10', 'SA_CAT_SINT_ZIKA', NULL, '0', 10, '0', 'Dolor de garganta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_11', '11', 'SA_CAT_SINT_ZIKA', NULL, '0', 11, '0', 'Mocos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_SINT_ZIKA_12', '12', 'SA_CAT_SINT_ZIKA', NULL, '0', 12, '0', 'Toz');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugares ha visto larvas de zika SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_01', '1', 'SA_CAT_LUG_LARVA', NULL, '0', 1, '0', 'Barriles');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_02', '2', 'SA_CAT_LUG_LARVA', NULL, '0', 2, '0', 'Floreros');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_03', '3', 'SA_CAT_LUG_LARVA', NULL, '0', 3, '0', 'Botellas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_04', '4', 'SA_CAT_LUG_LARVA', NULL, '0', 4, '0', 'Botellas de vidrio quebrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_05', '5', 'SA_CAT_LUG_LARVA', NULL, '0', 5, '0', 'Plantas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_06', '6', 'SA_CAT_LUG_LARVA', NULL, '0', 6, '0', 'Canales de techo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_998', '998', 'SA_CAT_LUG_LARVA', NULL, '0', 8, '0', 'Otros lugares');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_08', '8', 'SA_CAT_LUG_LARVA', NULL, '0', 7, '0', 'Llantas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('SA_CAT_LUG_LARVA_07', '7', 'SA_CAT_LUG_LARVA', NULL, '0', 9, '0', 'No ha visto');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo de enfermedades crónicas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_01', '1', 'ENFERMEDAD_CRN', NULL, '0', 1, '0', 'Cáncer de cualquier tipo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_02', '2', 'ENFERMEDAD_CRN', NULL, '0', 2, '0', 'Cardiopatías');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_03', '3', 'ENFERMEDAD_CRN', NULL, '0', 3, '0', 'Enfermedades hematológicas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_04', '4', 'ENFERMEDAD_CRN', NULL, '0', 4, '0', 'Enfermedades Inmunosupresoras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_05', '5', 'ENFERMEDAD_CRN', NULL, '0', 5, '0', 'Enfermedades Renales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_06', '6', 'ENFERMEDAD_CRN', NULL, '0', 6, '0', 'Epilepsia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_07', '7', 'ENFERMEDAD_CRN', NULL, '0', 7, '0', 'Leucemia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_08', '8', 'ENFERMEDAD_CRN', NULL, '0', 8, '0', 'Metabólica crónica (Diabetes)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_09', '9', 'ENFERMEDAD_CRN', NULL, '0', 9, '0', 'Otra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_10', '10', 'ENFERMEDAD_CRN', NULL, '0', 10, '0', 'Trastornos Psiquiatricos/Depresión');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_11', '11', 'ENFERMEDAD_CRN', NULL, '0', 11, '0', 'Asma');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_12', '12', 'ENFERMEDAD_CRN', NULL, '0', 12, '0', 'Hipertensión arterial');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_13', '13', 'ENFERMEDAD_CRN', NULL, '0', 13, '0', 'Artritis Reumatoide');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ENFERMEDAD_CRN_14', '14', 'ENFERMEDAD_CRN', NULL, '0', 14, '0', 'Tuberculosis');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST', NULL, NULL, NULL, '1', 0, '0', 'Catalogo de centros de estudios');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_002', '2', 'CENTRO_EST', NULL, '0', 1, '0', 'Centro Cristiano Evangel�stico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_004', '4', 'CENTRO_EST', NULL, '0', 2, '0', 'Coraz�n De Jes�s');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_006', '6', 'CENTRO_EST', NULL, '0', 3, '0', 'Instituto Gaspar Garc�a Laviana');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_007', '7', 'CENTRO_EST', NULL, '0', 4, '0', 'Jos� Dolores Estrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_008', '8', 'CENTRO_EST', NULL, '0', 5, '0', 'Jos� Santos Zelaya');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_009', '9', 'CENTRO_EST', NULL, '0', 6, '0', 'Capilla Del Calvario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_011', '11', 'CENTRO_EST', NULL, '0', 7, '0', 'Salom�n De La Selva/ Paul Harris/ La Aceitera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_014', '14', 'CENTRO_EST', NULL, '0', 8, '0', 'Centro Escolar El Alba');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_016', '16', 'CENTRO_EST', NULL, '0', 9, '0', 'San Mart�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_022', '22', 'CENTRO_EST', NULL, '0', 10, '0', 'Panamericana/ Zulema Baltodano');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_025', '25', 'CENTRO_EST', NULL, '0', 11, '0', 'Pedro Joaqu�n Chamorro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_028', '28', 'CENTRO_EST', NULL, '0', 12, '0', 'Parroquial Santa Ana');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_030', '30', 'CENTRO_EST', NULL, '0', 13, '0', 'Enmanuel');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_034', '34', 'CENTRO_EST', NULL, '0', 14, '0', 'Gasparin');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_038', '38', 'CENTRO_EST', NULL, '0', 15, '0', 'Abejita Huch');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_041', '41', 'CENTRO_EST', NULL, '0', 16, '0', 'Integral De Managua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_042', '42', 'CENTRO_EST', NULL, '0', 17, '0', 'Iglesia Cristo Del Rosario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_043', '43', 'CENTRO_EST', NULL, '0', 18, '0', 'San Sebasti�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_044', '44', 'CENTRO_EST', NULL, '0', 19, '0', 'Redentor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_046', '46', 'CENTRO_EST', NULL, '0', 20, '0', 'Nuestra Señora De La Merced (Walter Garcia)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_049', '49', 'CENTRO_EST', NULL, '0', 21, '0', 'Guardabarranco');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_055', '55', 'CENTRO_EST', NULL, '0', 22, '0', 'Instituto Cristiano Jerusal�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_064', '64', 'CENTRO_EST', NULL, '0', 23, '0', 'Monte De Sion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_076', '76', 'CENTRO_EST', NULL, '0', 24, '0', 'William Diaz Romero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_077', '77', 'CENTRO_EST', NULL, '0', 25, '0', 'Nicaraguita');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_089', '89', 'CENTRO_EST', NULL, '0', 26, '0', 'Casa Comunal San Jos�');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_096', '96', 'CENTRO_EST', NULL, '0', 27, '0', 'Jerusal�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_124', '124', 'CENTRO_EST', NULL, '0', 28, '0', 'Rayito De Luz');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_126', '126', 'CENTRO_EST', NULL, '0', 29, '0', 'Pre-Escolar Comunitario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_141', '141', 'CENTRO_EST', NULL, '0', 30, '0', 'Oasis De Esperanza');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_143', '143', 'CENTRO_EST', NULL, '0', 31, '0', 'Pre Escolar Sacuanjoche');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_153', '153', 'CENTRO_EST', NULL, '0', 32, '0', 'Instituto Monseñor Lezcano');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_201', '201', 'CENTRO_EST', NULL, '0', 33, '0', 'Casa Comunal Boer "Hormiguita"');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_202', '202', 'CENTRO_EST', NULL, '0', 34, '0', 'Casa Comunal Juan Pablo II');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_203', '203', 'CENTRO_EST', NULL, '0', 35, '0', 'CDI Monseñor Lezcano');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_204', '204', 'CENTRO_EST', NULL, '0', 36, '0', 'CDI San Sebasti�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_209', '209', 'CENTRO_EST', NULL, '0', 37, '0', 'Centro Infantil CDI Niño De Jes�s');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_211', '211', 'CENTRO_EST', NULL, '0', 38, '0', 'Colegio Bautista Redentor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_226', '226', 'CENTRO_EST', NULL, '0', 39, '0', 'Comedor Pablo Mar�a');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_227', '227', 'CENTRO_EST', NULL, '0', 40, '0', 'Escuela Delfincitos Del B�er');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_233', '233', 'CENTRO_EST', NULL, '0', 41, '0', 'Colegio Silviano Matamoros');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_235', '235', 'CENTRO_EST', NULL, '0', 49, '0', 'Otra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_237', '237', 'CENTRO_EST', NULL, '0', 42, '0', 'Pre-Escolar Enmanuel');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_238', '238', 'CENTRO_EST', NULL, '0', 43, '0', 'Pre-Escolar Gaspar�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_239', '239', 'CENTRO_EST', NULL, '0', 44, '0', 'Pre-Escolar Los Cariñositos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_240', '240', 'CENTRO_EST', NULL, '0', 45, '0', 'Pre-Escolar Sacuanjoche');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_241', '241', 'CENTRO_EST', NULL, '0', 46, '0', 'Preescolar San Jos�');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_243', '243', 'CENTRO_EST', NULL, '0', 47, '0', 'Colegio Romanos 8:28');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CENTRO_EST_244', '244', 'CENTRO_EST', NULL, '0', 48, '0', 'Colegio Huellas De Dios');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL_01', '1', 'CAT_TIPO_TEL', NULL, '0', 1, '0', 'Celular');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL_02', '2', 'CAT_TIPO_TEL', NULL, '0', 2, '0', 'Convencional');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo operadoras de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_01', '1', 'CAT_OPER_TEL', NULL, '0', 1, '0', 'Movistar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_02', '2', 'CAT_OPER_TEL', NULL, '0', 2, '0', 'Claro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_03', '3', 'CAT_OPER_TEL', NULL, '0', 3, '0', 'Cootel');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_RECEP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de recepci�n de muestras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_RECEP_01', '1', 'CAT_LUGAR_RECEP', NULL, '0', 1, '0', 'Auditorio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_RECEP_02', '2', 'CAT_LUGAR_RECEP', NULL, '0', 2, '0', 'Terreno');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo de interaccion participantes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_01', '1', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 1, '0', 'Contacto f�sico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_02', '2', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 2, '0', 'Jugaron juntos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_03', '3', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 3, '0', 'Comieron juntos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_04', '4', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 4, '0', 'Conversaron');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_05', '5', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 5, '0', 'Estuvieron en la misma habitaci�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_06', '6', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 6, '0', '�Cuid� a un miembro del hogar que estaba enfermo?');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_INTERACCION_07', '7', 'CHF_CAT_TIPO_INTERACCION', NULL, '0', 7, '0', '�Durmi� en la misma habitaci�n que esta persona?');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de interaccion participantes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION_01', '1', 'CHF_CAT_TIEMPO_INTERACCION', NULL, '0', 1, '0', '00-15 Minutos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION_02', '2', 'CHF_CAT_TIEMPO_INTERACCION', NULL, '0', 2, '0', '15-30 Minutos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION_03', '3', 'CHF_CAT_TIEMPO_INTERACCION', NULL, '0', 3, '0', '30-60 Minutos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION_04', '4', 'CHF_CAT_TIEMPO_INTERACCION', NULL, '0', 4, '0', 'Mas de 60 Minutos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIEMPO_INTERACCION_05', '5', 'CHF_CAT_TIEMPO_INTERACCION', NULL, '0', 5, '0', 'No tuvo contacto');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_P', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon no visita persona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_P_01', '1', 'CHF_CAT_VISITA_NO_P', NULL, '0', 1, '0', 'No estaba en la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_P_02', '2', 'CHF_CAT_VISITA_NO_P', NULL, '0', 2, '0', 'No puede atender en este momento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_P_03', '3', 'CHF_CAT_VISITA_NO_P', NULL, '0', 3, '0', 'No hay tutor presente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_P_04', '9', 'CHF_CAT_VISITA_NO_P', NULL, '0', 4, '0', 'Otra razon');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_C', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon no visita casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_C_01', '4', 'CHF_CAT_VISITA_NO_C', NULL, '0', 1, '0', 'Casa cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_C_02', '5', 'CHF_CAT_VISITA_NO_C', NULL, '0', 2, '0', 'No encuentro la direcci�n');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VISITA_NO_C_04', '9', 'CHF_CAT_VISITA_NO_C', NULL, '0', 3, '0', 'Otra razon');

/*Usuarios*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'username', 'Usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userdesc', 'Descripcion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'useremail', 'Correo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userlock', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'usercred', 'Contrasena vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userexp', 'Cuenta vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userroles', 'Roles','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'user.updated', 'Usuario actualizado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'user.created', 'Usuario creado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'profile', 'Perfil','0','0',0);
/*Accesos*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'access', 'Accesos de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lastAccess', 'Ultimo acceso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCredentials', 'Ultimo cambio de contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session', 'Id de sesion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ipaddress', 'Direccion IP','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logindate', 'Fecha ingreso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logoutdate', 'Fecha salida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logouturl', 'URL salida','0','0',0);

/*Audit trail*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'audittrail', 'Bitacora de cambios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityClass', 'Clase','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityName', 'Nombre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityProperty', 'Propiedad','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyOldValue', 'Valor anterior','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyNewValue', 'Nuevo valor','0','0',0);

/*Roles*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_ADMIN', 'Administrador','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_SUPER', 'Supervisor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_QC', 'Usuario web','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_MOVIL', 'Usuario movil','0','0',0);

/*Metadata*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'createdBy', 'Creado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCreated', 'Fecha creacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'modifiedBy', 'Modificado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateModified', 'Fecha de modificacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'active', 'Activo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'addedBy', 'Agregado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateAdded', 'Fecha','0','0',0);

/*Acciones, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'actions', 'Acciones','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'add', 'Agregar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'save', 'Guardar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'edit', 'Editar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'back', 'Regresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'cancel', 'Cancelar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'disable', 'Deshabilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enable', 'Habilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'end', 'Finalizar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'unlock', 'Desbloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lock', 'Bloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'export', 'Exportar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ok', 'Aceptar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'search', 'Buscar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'confirm', 'Confirmar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'please.enter', 'Favor ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('delete', 'Eliminar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('generate', 'Generar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('parameter', 'Par�metro', '0','0',0);

/*Mensajes generales, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enabled', 'Habilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'locked', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notenabled', 'Deshabilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notlocked', 'Desbloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.errors', 'Han ocurrido errores en el proceso!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.success', 'El proceso se ha completado exitosamente!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'noResults', 'No hay registros!','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userstudies', 'Estudios','0','0',0);

/*Cambio contrasenia*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'changepass', 'Cambiar contraseña..','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'credentials.expired', 'Su contrasena ha caducado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pass.updated', 'Su contrasena ha sido actualizada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'password.repeat', 'Repita la contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'Pattern.password.format', 'Al menos 8 caracteres combinando mayusculas, minusculas, numeros y caracteres especiales','0','0',0);

/* versiones de cartas (SCAN CARTAS)*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'select', 'Seleccione','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'validFrom', 'V�lido Desde','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'validUntil', 'V�lido Hasta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'version', 'Versi�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'study', 'Estudio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'recordDisabled', 'Registro esta inactivo!','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo s�ntomas de zika SA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_01', '1', 'CAT_VERSION_CARTA', NULL, '0', 1, '0', 'Version 1.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_02', '2', 'CAT_VERSION_CARTA', NULL, '0', 2, '0', 'Version 2.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_03', '3', 'CAT_VERSION_CARTA', NULL, '0', 3, '0', 'Version 3.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_04', '4', 'CAT_VERSION_CARTA', NULL, '0', 4, '0', 'Version 4.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_05', '5', 'CAT_VERSION_CARTA', NULL, '0', 5, '0', 'Version 5.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_06', '6', 'CAT_VERSION_CARTA', NULL, '0', 6, '0', 'Version 6.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_07', '7', 'CAT_VERSION_CARTA', NULL, '0', 7, '0', 'Version 7.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_08', '8', 'CAT_VERSION_CARTA', NULL, '0', 8, '0', 'Version 8.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_09', '9', 'CAT_VERSION_CARTA', NULL, '0', 9, '0', 'Version 9.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_10', '10', 'CAT_VERSION_CARTA', NULL, '0', 10, '0', 'Version 10.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_11', '11', 'CAT_VERSION_CARTA', NULL, '0', 11, '0', 'Version 11.0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_98', '98', 'CAT_VERSION_CARTA', NULL, '0', 12, '0', 'Marzo 2012');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERSION_CARTA_99', '99', 'CAT_VERSION_CARTA', NULL, '0', 13, '0', '-');

/*Monitoreo intensivo*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'participant.code', 'C�digo Participante','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'house', 'Casa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'positive', 'Positivo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fif', 'FIF','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'participant', 'Participante','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'age', 'Edad','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'cases', 'Casos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'print.selected', 'Imprimir seleccionado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visit', 'Visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'label.types', 'Tipos de Etiquetas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'close.case', 'Cerrar Caso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'select.case', 'Seleccione al menos un caso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'inactive.case', 'Caso ya no est� activo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'participants', 'Participantes','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visitas monitoreo intensivo CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_01', '1', 'CHF_CAT_VIS_MI', NULL, '0', 1, '0', 'Visita 1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_02', '2', 'CHF_CAT_VIS_MI', NULL, '0', 2, '0', 'Visita 2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_03', '3', 'CHF_CAT_VIS_MI', NULL, '0', 3, '0', 'Visita 3');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_04', '4', 'CHF_CAT_VIS_MI', NULL, '0', 4, '0', 'Visita 4');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_05', '5', 'CHF_CAT_VIS_MI', NULL, '0', 5, '0', 'Visita 5');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_VIS_MI_F', 'F', 'CHF_CAT_VIS_MI', NULL, '0', 6, '0', 'Visita Final');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_ETIQ_MI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de etiquetas monitoreo intensivo CHF');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_ETIQ_MI_01', '1', 'CHF_CAT_TIP_ETIQ_MI', NULL, '0', 1, '0', 'BHC');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_ETIQ_MI_02', '2', 'CHF_CAT_TIP_ETIQ_MI', NULL, '0', 2, '0', 'PBMC');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_ETIQ_MI_03', '3', 'CHF_CAT_TIP_ETIQ_MI', NULL, '0', 3, '0', 'Rojo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIP_ETIQ_MI_04', '4', 'CHF_CAT_TIP_ETIQ_MI', NULL, '0', 4, '0', 'FLU');

/*Impresi�n de muestras*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sample.code', 'C�digo muestra','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'start.date.registration', 'Fecha Inicio de Registro','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'end.date.registration', 'Fecha Fin de Registro','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sample.date', 'Fecha Toma','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'label.types.to.generate', 'Tipos de Etiquetas a Generar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enter.parameter', 'Favor ingresar al menos un par�metro de b�squeda','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'registered', 'Registrado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'select.sample', 'Seleccione al menos una muestra','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visit', 'Visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitDate', 'Fecha visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitDuplicated', 'Visita ya fu� ingresada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitTime', 'Hora visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitHour', 'Hora probable siguiente visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitExp', 'Expediente?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitTemp', 'Temperatura','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'start.visitDate', 'Fecha Inicio Visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'end.visitDate', 'Fecha Fin Visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visits', 'Visitas','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sympt', 'Sintomas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sintDuplicated', 'Sintomas de esta fecha ya fueron ingresados!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fechaSintomas', 'Fecha de Sintomas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fiebre', 'Fiebre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fiebreCuantificada', 'Fiebre cuantificada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'valorFiebreCuantificada', 'Valor fiebre cuantificada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dolorCabeza', 'Dolor de cabeza','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dolorArticular', 'Dolor articular','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dolorMuscular', 'Dolor muscular','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dificultadRespiratoria', 'Dificultad respiratoria','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fdr', 'Fecha dificultad respiratoria','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'secrecionNasal', 'Secrecion nasal','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fsn', 'Fecha secrecion nasal','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tos', 'Tos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ftos', 'Fecha tos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pocoApetito', 'pocoApetito','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dolorGarganta', 'Dolor de garganta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'diarrea', 'Diarrea','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'quedoCama', 'Quedo en cama','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'respiracionRuidosa', 'Respiracion ruidosa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'frr', 'Fecha dolor de garganta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'oseltamivir', 'oseltamivir','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'antibiotico', 'Antibiotico','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'cualAntibiotico', 'Cual antibiotico?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'prescritoMedico', 'Fue prescrito por m�dico?','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'contact', 'Contactos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'contDuplicated', 'Este contacto ya fu� ingresado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'partContacto', 'Miembro del hogar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fechaContacto', 'Fecha contacto','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tiempoInteraccion', '�Cu�nto tiempo interactu� con esta persona hoy (minutos)?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tipoInteraccion', 'Qu� tipo de interacci�n tuvo con esta persona (marque todos los que correspondan)?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tiempo', 'Tiempo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tipo', 'Tipo','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'failVisit', 'Visita fallida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'razonVisitaFallida', 'Raz�n por la que falla la visita','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'otraRazon', 'Describa que otro motivo','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'housesearch', 'Buscar casa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'code', 'C�digo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'familyChief', 'Jefe Familia','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'initDate', 'Fecha de inicio','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'expCS', 'Tiene expediente en el CS?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'temp', 'Temperatura','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sample', 'Muestra','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tomaMxSn', 'Se toma la muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tipoMuestra', 'Tipo de muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'hora', 'Hora de muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'horaFin', 'Hora fin de muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'codigoMx', 'C�digo de muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'volumen', 'Volumen','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'observacion', 'Observaci�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'descOtraObservacion', 'Otra Observaci�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'razonNoToma', 'Porque no toma la muestra?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'descOtraRazonNoToma', 'Otra raz�n','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'numPinchazos', 'N�mero de pinchazos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tubo', 'Tipo de tubo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tubo', 'Tipo de tubo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'proposito', 'Prop�sito de muestra','0','0',0);   	
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'realizaPaxgene', 'Se realiza paxgene?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'horaInicioPax', 'Hora inicio paxgene','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'horaFinPax', 'Hora fin paxgene','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'visitaFinal', 'Datos de la visita final','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enfermo', '�Actualmente est� enfermo?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'consTerreno', 'Consulta m�dica en terreno','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'referidoCs', 'Referido a centro de salud','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tratamiento', 'Si esta enfermo que tratamiento tom�','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sintResp', '�El participante presento s�ntomas respiratorios en el periodo que no fue visitado por el personal del CSSFV?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fi', 'Fecha de Inicio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ff', 'Fecha en que Termina','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pend', 'pendiente','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tratamiento monitoreo intensivo CHF visita final');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO_01', 'ACET', 'CHF_CAT_TRATAMIENTO', NULL, '0', 1, '0', 'Acetaminofen');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO_02', 'ANTBIO', 'CHF_CAT_TRATAMIENTO', NULL, '0', 2, '0', 'Antibi�tico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO_03', 'OSEL', 'CHF_CAT_TRATAMIENTO', NULL, '0', 3, '0', 'Oseltamivir');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO_04', 'ANTGRI', 'CHF_CAT_TRATAMIENTO', NULL, '0', 4, '0', 'Antigripales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TRATAMIENTO_09', 'NING', 'CHF_CAT_TRATAMIENTO', NULL, '0', 4, '0', 'Ninguno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo relacion familiar tutor cohorte pediatrica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_01', '1', 'CP_CAT_RFTUTOR', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_02', '2', 'CP_CAT_RFTUTOR', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_03', '3', 'CP_CAT_RFTUTOR', NULL, '0', 3, '0', 'Abuelo (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_04', '4', 'CP_CAT_RFTUTOR', NULL, '0', 4, '0', 'Tio (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_05', '5', 'CP_CAT_RFTUTOR', NULL, '0', 5, '0', 'Hermano (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_06', '6', 'CP_CAT_RFTUTOR', NULL, '0', 6, '0', 'Otra relación familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_07', '7', 'CP_CAT_RFTUTOR', NULL, '0', 7, '0', 'Sin Relación Familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RFTUTOR_08', '8', 'CP_CAT_RFTUTOR', NULL, '0', 8, '0', 'Participante');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_CI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo criterios de inclusion PDCS');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_CI_01', '1', 'CP_CAT_CI', NULL, '0', 1, '0', 'Vivir en área de cobertura del CSSFV.');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_CI_02', '3', 'CP_CAT_CI', NULL, '0', 2, '0', 'Tener la intención de permanecer en el área durante el estudio.');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de residencia PDCS');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TR_01', '1', 'CP_CAT_TR', NULL, '0', 1, '0', 'Menos de Seis Meses');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TR_02', '2', 'CP_CAT_TR', NULL, '0', 2, '0', 'Seis Meses a Dos Años');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TR_03', '3', 'CP_CAT_TR', NULL, '0', 3, '0', 'Dos Años ó Más');

/*Permisos de usuario*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'permission', 'Permisos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'encuestas', 'Encuestas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'muestra', 'Muestras','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ecasa', 'Casas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'elactancia', 'Lactancia Materna','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'esatisfaccion', 'Satisfacción','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'obsequio', 'Obsequio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pesotalla', 'Peso y Talla','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'datosparto', 'Datos Parto BB','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'vacunas', 'Vacunas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'recepcion', 'Recepcion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'consentimiento', 'Consentimiento','0','0',0);


/*Reconsentimiento dengue extensión de edad 2018*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visita no exitosa CP');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_01', '1', 'CP_CAT_NV', NULL, '0', 1, '0', 'Casa Cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_02', '2', 'CP_CAT_NV', NULL, '0', 2, '0', 'Niño ausente (Solo si tiene 6 años o mas)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_03', '4', 'CP_CAT_NV', NULL, '0', 3, '0', 'No se encontro la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_04', '5', 'CP_CAT_NV', NULL, '0', 4, '0', 'Padres Ausentes o Adultos Ausentes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_05', '6', 'CP_CAT_NV', NULL, '0', 5, '0', 'Se cambiaron de casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_06', '7', 'CP_CAT_NV', NULL, '0', 6, '0', 'No se encontro tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_07', '8', 'CP_CAT_NV', NULL, '0', 7, '0', 'Acude a Consulta Medica sin Tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_08', '9', 'CP_CAT_NV', NULL, '0', 8, '0', 'Fuera del pais');
/*Muestreo Covid19 2020*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NV_998', '998', 'CP_CAT_NV', NULL, '0', 9, '0', 'Otro motivo');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TV', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo de vivienda');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TV_01', '1', 'CP_CAT_TV', NULL, '0', 1, '0', 'Propia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_TV_02', '2', 'CP_CAT_TV', NULL, '0', 2, '0', 'Alquilada');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tratamiento reconsentimiento dengue 2018');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_01', '1', 'CPD_CAT_TRATAMIENTO', NULL, '0', 1, '0', 'Salbutamol');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_02', '2', 'CPD_CAT_TRATAMIENTO', NULL, '0', 2, '0', 'Prednisona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_03', '3', 'CPD_CAT_TRATAMIENTO', NULL, '0', 3, '0', 'Enalapril');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_04', '4', 'CPD_CAT_TRATAMIENTO', NULL, '0', 4, '0', 'Isosorbide');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_05', '5', 'CPD_CAT_TRATAMIENTO', NULL, '0', 5, '0', 'Digoxina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_06', '6', 'CPD_CAT_TRATAMIENTO', NULL, '0', 6, '0', 'Hidroclorotiazida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_07', '7', 'CPD_CAT_TRATAMIENTO', NULL, '0', 7, '0', 'Ibuprofeno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_08', '8', 'CPD_CAT_TRATAMIENTO', NULL, '0', 8, '0', 'Carbamazepina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_09', '9', 'CPD_CAT_TRATAMIENTO', NULL, '0', 9, '0', 'Acido Valproico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_10', '10', 'CPD_CAT_TRATAMIENTO', NULL, '0', 10, '0', 'Furosemida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_11', '11', 'CPD_CAT_TRATAMIENTO', NULL, '0', 11, '0', 'Espirinolactona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_TRATAMIENTO_998', '998', 'CPD_CAT_TRATAMIENTO', NULL, '0', 12, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_MOTRECHAZO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta dengue');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_MOTRECHAZO_01', '1', 'CPD_CAT_MOTRECHAZO', NULL, '0', 1, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_MOTRECHAZO_02', '2', 'CPD_CAT_MOTRECHAZO', NULL, '0', 2, '0', 'No desea continuar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_MOTRECHAZO_03', '3', 'CPD_CAT_MOTRECHAZO', NULL, '0', 3, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CPD_CAT_MOTRECHAZO_998', '4', 'CPD_CAT_MOTRECHAZO', NULL, '0', 4, '0', 'Otros motivos');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo cambio de tutor reconsentimientos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_01', '1', 'CP_CAT_DIFTUTOR', NULL, '0', 1, '0', 'Fallecido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_02', '2', 'CP_CAT_DIFTUTOR', NULL, '0', 2, '0', 'Privado de Libertad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_03', '3', 'CP_CAT_DIFTUTOR', NULL, '0', 3, '0', 'Fuera del Pais/Cuidad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_998', '4', 'CP_CAT_DIFTUTOR', NULL, '0', 8, '0', 'Otro Motivo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_05', '5', 'CP_CAT_DIFTUTOR', NULL, '0', 4, '0', 'Niño Abandonado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_06', '6', 'CP_CAT_DIFTUTOR', NULL, '0', 5, '0', 'Cambio de tutor:Madre a Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_07', '7', 'CP_CAT_DIFTUTOR', NULL, '0', 6, '0', 'Cambio de tutor:Padre a Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_DIFTUTOR_08', '8', 'CP_CAT_DIFTUTOR', NULL, '0', 7, '0', 'Corección de Nombre');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para verificar procedimientos con el tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_01', '1', 'CP_CAT_VERIFTUTOR', NULL, '0', 1, '0', 'Una copia del consentimiento ha sido entregada al padre/tutor del part.?');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_02', '2', 'CP_CAT_VERIFTUTOR', NULL, '0', 2, '0', 'Firmó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_03', '3', 'CP_CAT_VERIFTUTOR', NULL, '0', 3, '0', 'Fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_04', '4', 'CP_CAT_VERIFTUTOR', NULL, '0', 4, '0', 'Plasmó su huella digital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_05', '5', 'CP_CAT_VERIFTUTOR', NULL, '0', 5, '0', 'Testigo firmó y fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_VERIFTUTOR_06', '6', 'CP_CAT_VERIFTUTOR', NULL, '0', 6, '0', 'Entendió los procedimientos del estudio');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon no se logro georeferenciar el hogar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_01', '1', 'CP_CAT_NOGEO', NULL, '0', 1, '0', 'Equipo no logra precision de 5 mts');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_02', '2', 'CP_CAT_NOGEO', NULL, '0', 2, '0', 'Lluvia y/o tormenta eléctrica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_03', '3', 'CP_CAT_NOGEO', NULL, '0', 3, '0', 'Inseguridad de la zona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_998', '4', 'CP_CAT_NOGEO', NULL, '0', 6, '0', 'Otro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_05', '5', 'CP_CAT_NOGEO', NULL, '0', 4, '0', 'Se tomó punto con codigo relacionado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_NOGEO_06', '6', 'CP_CAT_NOGEO', NULL, '0', 5, '0', 'Consentimiento se realizó en el CSSFV');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RAZEMAN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon de emancipación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RAZEMAN_01', '1', 'CP_CAT_RAZEMAN', NULL, '0', 1, '0', 'Embarazada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RAZEMAN_02', '2', 'CP_CAT_RAZEMAN', NULL, '0', 2, '0', 'Casad@');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RAZEMAN_03', '3', 'CP_CAT_RAZEMAN', NULL, '0', 3, '0', 'Con hijos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CP_CAT_RAZEMAN_998', '4', 'CP_CAT_RAZEMAN', NULL, '0', 4, '0', 'Otro');

/*Reportes*/
/*Datos generales participante para expediente*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'title.report.file', 'Datos Generales del Participante del Estudio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.code', 'Código:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.date', 'Fecha:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.general.data', '1. Datos Generales','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.family', 'Familia:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.family.boss', 'Jefe de Familia:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.tutor', 'Tutor:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.father', 'Padre:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.mother', 'Madre:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.family.relationship', 'Relación del tutor con el niño:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.housing.type', 'Vivienda:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.neighborhood', 'Barrio:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.block', 'Manzana:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.address', 'Dirección:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.phone', 'Teléfono:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.contact', 'Contacto:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.contact.explanation', 'Si no lo localizamos, a quién podemos contactar que no viva con Ud.:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.name', 'Nombre:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.child.data', '2. Datos del Niño','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.names.surnames', 'Nombres y Apellidos:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.age', 'Edad:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.birthdate', 'Fecha de Nacimiento:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.gender', 'Sexo:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.student', 'Estudiante:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.turn', 'Turno:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.school', 'Escuela:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.residence.time', 'Tiempo de Residencia:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.stay.area', 'Intensión de quedarse en el barrio por los tres años siguientes:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.where.child.attends', 'Donde asiste cuanto tiene problemas de salud el niño:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.health.unit.attends', 'Nombre de la unidad de salud a la que asiste su niño:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.willing.attends.cssf', 'Estaría dispuesto a asistir solamente al C/S Sócrates Flores si el niño se enferma:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.clinical.epi.data', '3. Datos clínicos y epidemiológicos del Niño:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.chronic disease', 'El nino padece de alguna enfermedad crónica:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.wich', 'Cuál:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.take.treatment', 'Toma algún tratamiento:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.diagnosed.dengue', 'Se le ha diagnosticado dengue en alguna ocasión:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.when', 'Cuándo:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.hospitalized.dengue', 'Ha sido hospitalizao por dengue:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.digitador', 'Digitador:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.participant.not.found', 'Participante no encontrado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.contact.not.found', 'No se encontró información de contacto','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.consent.not.found', 'No se encontró carta de consentimiento','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.participant survey.not.found', 'No se encontro encuesta de participante','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.all', 'Todos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('MOTIVO_OBSEQUIO', NULL, NULL, NULL, '1', 0, '0', 'Motivo de entrega de obsequio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('MOTIVO_OBSEQUIO_01', '1', 'MOTIVO_OBSEQUIO', NULL, '0', 1, '0', 'Seguimiento Inicial');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('MOTIVO_OBSEQUIO_02', '2', 'MOTIVO_OBSEQUIO', NULL, '0', 2, '0', 'Seguimiento Final');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA', NULL, NULL, NULL, '1', 0, '0', 'Nivel de Conciencia hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_1', '1', 'NIVELCONCIENCIA', NULL, '0', 1, '0', 'Claro y Lúcido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_2', '2', 'NIVELCONCIENCIA', NULL, '0', 2, '0', 'Alerta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_3', '3', 'NIVELCONCIENCIA', NULL, '0', 3, '0', 'Agitado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_4', '4', 'NIVELCONCIENCIA', NULL, '0', 4, '0', 'Irritable');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_5', '5', 'NIVELCONCIENCIA', NULL, '0', 5, '0', 'Letargico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('NIVELCONCIENCIA_6', '6', 'NIVELCONCIENCIA', NULL, '0', 6, '0', 'Activo');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('EXTREMIDADES', NULL, NULL, NULL, '1', 0, '0', 'Extremidades hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('EXTREMIDADES_Calidez', '1', 'EXTREMIDADES', NULL, '0', 1, '0', 'Calidez');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('EXTREMIDADES_Frías', '2', 'EXTREMIDADES', NULL, '0', 2, '0', 'Frías');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('EXTREMIDADES_Muy_Frías_Húmedas', '3', 'EXTREMIDADES', NULL, '0', 3, '0', 'Muy Frías y Húmedas');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PULSOCALIDAD', NULL, NULL, NULL, '1', 0, '0', 'Pulso Calidad hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PULSOCALIDAD_FuerteVigoroso', '1', 'PULSOCALIDAD', NULL, '0', 1, '0', 'Fuerte, Vigoroso');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PULSOCALIDAD_DebilFiliforme', '2', 'PULSOCALIDAD', NULL, '0', 2, '0', 'Débil y Filiforme');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PULSOCALIDAD_MuyDebil', '3', 'PULSOCALIDAD', NULL, '0', 3, '0', 'Muy Débil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PULSOCALIDAD_Ausente', '4', 'PULSOCALIDAD', NULL, '0', 4, '0', 'Ausente');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CLASIFICACIONDENGUE', NULL, NULL, NULL, '1', 0, '0', 'ClasificacionDengue hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CLASIFICACIONDENGUE_SinAlarma', '1', 'CLASIFICACIONDENGUE', NULL, '0', 1, '0', 'Sin signo de Alarma');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CLASIFICACIONDENGUE_ConAlarma', '2', 'CLASIFICACIONDENGUE', NULL, '0', 2, '0', 'Con signo de Alarma');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CLASIFICACIONDENGUE_Grave', '3', 'CLASIFICACIONDENGUE', NULL, '0', 3, '0', 'Grave');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('LLENADOCAPILAR', NULL, NULL, NULL, '1', 0, '0', 'LlenadoCapilar hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('LLENADOCAPILAR_>2', '1', 'LLENADOCAPILAR', NULL, '0', 1, '0', '>2"');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('LLENADOCAPILAR_3-5', '2', 'LLENADOCAPILAR', NULL, '0', 2, '0', '3-5');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('LLENADOCAPILAR_>=5', '3', 'LLENADOCAPILAR', NULL, '0', 3, '0', '>=5');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('LLENADOCAPILAR_<2', '4', 'LLENADOCAPILAR', NULL, '0', 4, '0', '<2"');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PERSONAVALIDA', NULL, NULL, NULL, '1', 0, '0', 'PersonaValida hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PERSONAVALIDA_16951', '16951', 'PERSONAVALIDA', NULL, '0', 1, '0', 'Sergio Ojeda Munguía');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PERSONAVALIDA_28613', '28613', 'PERSONAVALIDA', NULL, '0', 2, '0', 'Miguel Plazaola');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PERSONAVALIDA_26861', '26861', 'PERSONAVALIDA', NULL, '0', 3, '0', 'Nerys Sánchez');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('PERSONAVALIDA_377', '377', 'PERSONAVALIDA', NULL, '0', 17, '0', ' Dr. PAUL ARANCIBIA TORRES');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('DIURESIS', NULL, NULL, NULL, '1', 0, '0', 'diuresis hemodinamica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('DIURESIS_1-5', '1', 'DIURESIS', NULL, '0', 1, '0', '1-5 ml/Kg/Hr');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('DIURESIS_0.5-0.9', '2', 'DIURESIS', NULL, '0', 2, '0', '0.5-0.9 ml/Kg/Hr');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('DIURESIS_0.5', '3', 'DIURESIS', NULL, '0', 3, '0', '>0.5 ml/Kg/Hr');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de muestras superficie');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_01', '01', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 1, '0', 'Manecilla de la puerta de la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_02', '02', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 2, '0', 'Manija del baño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_03', '03', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 3, '0', 'Manija del refrigerador');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_04', '04', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 4, '0', 'Interruptor de la luz sala o habitación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_05', '05', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 5, '0', 'Juguete de niño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_06', '06', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 6, '0', 'Teléfono celular');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_07', '07', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 7, '0', 'Mesa del comedor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_08', '08', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 8, '0', 'Grifo de agua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_09', '09', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 9, '0', 'Encima del refrigerador');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_10', '10', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 10, '0', 'Mueble cerca de la cama');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_11', '11', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 11, '0', 'Pared detrás de la cama');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_12', '12', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 12, '0', 'Pared en el baño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CHF_CAT_TIPO_MX_SUP_13', '13', 'CHF_CAT_TIPO_MX_SUP', NULL, '0', 13, '0', 'Muestra de ambas manos');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_VISITA', NULL, NULL, NULL, '1', 0, '0', 'Tipos de visita UO1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_VISITA_I', 'I', 'UO1_CAT_VISITA', NULL, '0', 1, '0', 'Inicial');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_VISITA_F', 'F', 'UO1_CAT_VISITA', NULL, '0', 2, '0', 'Final');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_POSITIVO_POR', NULL, NULL, NULL, '1', 0, '0', 'Caso UO1 positivo por');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_POSITIVO_01', '01', 'UO1_CAT_POSITIVO_POR', NULL, '0', 1, '0', 'PCR');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_POSITIVO_02', '02', 'UO1_CAT_POSITIVO_POR', NULL, '0', 2, '0', 'PRI');

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.positive.by', 'Positivo por','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_REGISTRO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de registro evento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_REGISTRO_01', '1', 'CAT_LUGAR_REGISTRO', NULL, '0', 1, '0', 'Centro de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_REGISTRO_02', '2', 'CAT_LUGAR_REGISTRO', NULL, '0', 2, '0', 'Terreno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_INTENSIDAD_SIN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo intensidad sintomas transmision influenza');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_INTENSIDAD_SIN_01', '1', 'CAT_INTENSIDAD_SIN', NULL, '0', 1, '0', 'Leve');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_INTENSIDAD_SIN_02', '2', 'CAT_INTENSIDAD_SIN', NULL, '0', 2, '0', 'Moderad@');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_INTENSIDAD_SIN_03', '3', 'CAT_INTENSIDAD_SIN', NULL, '0', 3, '0', 'Sever@');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_SNDNA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido/No Aplica UO1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_SNDNA_01', '1', 'UO1_CAT_SNDNA', NULL, '0', 1, '0', 'S');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_SNDNA_02', '0', 'UO1_CAT_SNDNA', NULL, '0', 2, '0', 'N');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_SNDNA_03', '2', 'UO1_CAT_SNDNA', NULL, '0', 4, '0', 'NA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('UO1_CAT_SNDNA_999', '999', 'UO1_CAT_SNDNA', NULL, '0', 3, '0', 'D');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razones no completa informacion MA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_01', '1', 'CAT_RAZON_NO_DATA', NULL, '0', 1, '0', 'Acompañante desconoce informacion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_02', '2', 'CAT_RAZON_NO_DATA', NULL, '0', 2, '0', 'No se pudo tomar todas las muestras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_03', '3', 'CAT_RAZON_NO_DATA', NULL, '0', 3, '0', 'Abandono de Muestreo Anual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_04', '4', 'CAT_RAZON_NO_DATA', NULL, '0', 4, '0', 'No desea dar informacion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_05', '5', 'CAT_RAZON_NO_DATA', NULL, '0', 5, '0', 'Falta de tiempo por parte del acompañante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_06', '6', 'CAT_RAZON_NO_DATA', NULL, '0', 6, '0', 'Solo revisaba información');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_07', '7', 'CAT_RAZON_NO_DATA', NULL, '0', 10, '0', 'Otra razón');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_08', '8', 'CAT_RAZON_NO_DATA', NULL, '0', 7, '0', 'Solo se realiza peso y talla');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_09', '9', 'CAT_RAZON_NO_DATA', NULL, '0', 8, '0', 'Peso y talla en otro equipo móvil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_10', '10', 'CAT_RAZON_NO_DATA', NULL, '0', 9, '0', 'No comprendo las preguntas');

/*COVID19*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'covid19.positives', 'Positivos Covid19','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'covid19.participants.list', 'Lista de participantes','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'chf.house', 'Casa CHF','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'FIS', 'FIS','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_POSITIVO_POR', NULL, NULL, NULL, '1', 0, '0', 'Caso Covid19 positivo por');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_POSITIVO_01', '01', 'COVID_CAT_POSITIVO_POR', NULL, '0', 1, '0', 'Clínica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_POSITIVO_02', '02', 'COVID_CAT_POSITIVO_POR', NULL, '0', 2, '0', 'PCR');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visitas seguimiento Covid19');
/* para transmision*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_01', '1', 'COVID_CAT_VIS_SEG', NULL, '0', 1, '0', 'Visita 1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_02', '2', 'COVID_CAT_VIS_SEG', NULL, '0', 2, '0', 'Visita 2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_03', '3', 'COVID_CAT_VIS_SEG', NULL, '0', 3, '0', 'Visita 3');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_04', '4', 'COVID_CAT_VIS_SEG', NULL, '0', 4, '0', 'Visita 4');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_05', '5', 'COVID_CAT_VIS_SEG', NULL, '0', 5, '0', 'Visita 5');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_06', '6', 'COVID_CAT_VIS_SEG', NULL, '0', 6, '0', 'Visita 6');
/*para pediatrica*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_I', 'I', 'COVID_CAT_VIS_SEG', NULL, '0', 1, '0', 'Visita Inicial');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_VIS_SEG_F', 'F', 'COVID_CAT_VIS_SEG', NULL, '0', 7, '0', 'Visita Final');

/*Tamizaje TCovid*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_INDICE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo criterios de inclusion TCovid índice');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_INDICE_01', '1', 'COVID_CAT_CI_INDICE', NULL, '0', 1, '0', 'Vivir en área de cobertura del CSSFV.');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_INDICE_02', '2', 'COVID_CAT_CI_INDICE', NULL, '0', 2, '0', 'Ser participante en el estudio de Cohorte de familia de Influenza');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_MIEMBRO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo criterios de inclusion TCovid miembro del hogar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_MIEMBRO_01', '1', 'COVID_CAT_CI_MIEMBRO', NULL, '0', 1, '0', 'Participante estudio Dinámica de Transmisión de Influenza');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_CI_MIEMBRO_02', '2', 'COVID_CAT_CI_MIEMBRO', NULL, '0', 2, '0', 'Vivir con una persona que tiene coronavirus o síntomas de coronavirus');

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'covid19.candidates', 'Candidatos Transmisión Covid19','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'covid19.candidates.list', 'Lista de candidatos Transmisión Covid19','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'candidate', 'Candidato','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'screening', 'Tamizaje','0','0',0);

/*Sintomas Covid19*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo medicamentos que toma para Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_01', '1', 'COVID_MEDICAMENTO_SVC', NULL, '0', 1, '0', 'Cloroquina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_02', '2', 'COVID_MEDICAMENTO_SVC', NULL, '0', 2, '0', 'Hidoxicloroquina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_03', '3', 'COVID_MEDICAMENTO_SVC', NULL, '0', 3, '0', 'Azitromicina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_04', '4', 'COVID_MEDICAMENTO_SVC', NULL, '0', 4, '0', 'Nitazoxanida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_05', '5', 'COVID_MEDICAMENTO_SVC', NULL, '0', 5, '0', 'Ivermectina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_06', '6', 'COVID_MEDICAMENTO_SVC', NULL, '0', 6, '0', 'ASA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_07', '7', 'COVID_MEDICAMENTO_SVC', NULL, '0', 7, '0', 'Lopinavir/Ritonavir');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_08', '8', 'COVID_MEDICAMENTO_SVC', NULL, '0', 8, '0', 'Oseltamivir');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_09', '9', 'COVID_MEDICAMENTO_SVC', NULL, '0', 9, '0', 'Isoprinosine');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_10', '10', 'COVID_MEDICAMENTO_SVC', NULL, '0', 10, '0', 'Melatonina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_11', '11', 'COVID_MEDICAMENTO_SVC', NULL, '0', 11, '0', 'Colchicina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_12', '12', 'COVID_MEDICAMENTO_SVC', NULL, '0', 12, '0', 'Vitamina C');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_13', '13', 'COVID_MEDICAMENTO_SVC', NULL, '0', 13, '0', 'Enoxiparina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_14', '14', 'COVID_MEDICAMENTO_SVC', NULL, '0', 14, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_15', '15', 'COVID_MEDICAMENTO_SVC', NULL, '0', 15, '0', 'Oxigeno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_16', '16', 'COVID_MEDICAMENTO_SVC', NULL, '0', 16, '0', 'Antigripales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_17', '17', 'COVID_MEDICAMENTO_SVC', NULL, '0', 17, '0', 'Prednisona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_18', '18', 'COVID_MEDICAMENTO_SVC', NULL, '0', 18, '0', 'Ibuprofen');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_MEDICAMENTO_SVC_998', '998', 'COVID_MEDICAMENTO_SVC', NULL, '0', 19, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo factor riesgo Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_01', '1', 'COVID_FACTOR_RI_SVC', NULL, '0', 1, '0', 'HTA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_02', '2', 'COVID_FACTOR_RI_SVC', NULL, '0', 2, '0', 'DM TIPO 2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_03', '3', 'COVID_FACTOR_RI_SVC', NULL, '0', 3, '0', 'ASMA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_04', '4', 'COVID_FACTOR_RI_SVC', NULL, '0', 4, '0', 'TABAQUISMO');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_05', '5', 'COVID_FACTOR_RI_SVC', NULL, '0', 5, '0', 'OBESIDAD');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_06', '6', 'COVID_FACTOR_RI_SVC', NULL, '0', 6, '0', 'CANCER');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_FACTOR_RI_SVC_998', '998', 'COVID_FACTOR_RI_SVC', NULL, '0', 7, '0', 'OTRO');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugares visitados durante aislamiento Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_01', '1', 'COVID_LUGARES_AVC', NULL, '0', 1, '0', 'Mercado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_02', '2', 'COVID_LUGARES_AVC', NULL, '0', 2, '0', 'Supermercado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_03', '3', 'COVID_LUGARES_AVC', NULL, '0', 3, '0', 'Escuela');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_04', '4', 'COVID_LUGARES_AVC', NULL, '0', 4, '0', 'Universidad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_05', '5', 'COVID_LUGARES_AVC', NULL, '0', 5, '0', 'Transporte público');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_06', '6', 'COVID_LUGARES_AVC', NULL, '0', 6, '0', 'Centro de Salud u Hospital ');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_998', '998', 'COVID_LUGARES_AVC', NULL, '0', 7, '0', 'Otros');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_LUGARES_AVC_07', '7', 'COVID_LUGARES_AVC', NULL, '0', 8, '0', 'Ninguno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/No Responde Aislamiento Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNR_01', '1', 'COVID_CAT_SNNR', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNR_02', '0', 'COVID_CAT_SNNR', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNR_997', '997', 'COVID_CAT_SNNR', NULL, '0', 3, '0', 'No Responde');

/*Visita Final Covid19*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_ESTSALUD_HOSP', NULL, NULL, NULL, '1', 0, '0', 'Catalogo hospitalizacion por estado de salud Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_ESTSALUD_HOSP_01', '1', 'COVID_ESTSALUD_HOSP', NULL, '0', 1, '0', 'Grave');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_ESTSALUD_HOSP_02', '0', 'COVID_ESTSALUD_HOSP', NULL, '0', 2, '0', 'Muy Grave');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_ESTSALUD_HOSP_997', '997', 'COVID_ESTSALUD_HOSP', NULL, '0', 3, '0', 'Falleció');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo medicamentos que toma para Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_01', '1', 'COVID_TRATAMIENTO_VFC', NULL, '0', 1, '0', 'Acetaminofén');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_02', '2', 'COVID_TRATAMIENTO_VFC', NULL, '0', 2, '0', 'Antibiótico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_03', '3', 'COVID_TRATAMIENTO_VFC', NULL, '0', 3, '0', 'Cloroquina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_04', '4', 'COVID_TRATAMIENTO_VFC', NULL, '0', 4, '0', 'Hidoxicloroquina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_05', '5', 'COVID_TRATAMIENTO_VFC', NULL, '0', 5, '0', 'Azitromicina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_06', '6', 'COVID_TRATAMIENTO_VFC', NULL, '0', 6, '0', 'Nitazoxanida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_07', '7', 'COVID_TRATAMIENTO_VFC', NULL, '0', 7, '0', 'Ivermectina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_08', '8', 'COVID_TRATAMIENTO_VFC', NULL, '0', 8, '0', 'ASA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_09', '9', 'COVID_TRATAMIENTO_VFC', NULL, '0', 9, '0', 'Lopinavir/Ritonavir');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_10', '10', 'COVID_TRATAMIENTO_VFC', NULL, '0', 10, '0', 'Oseltamivir');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_11', '11', 'COVID_TRATAMIENTO_VFC', NULL, '0', 11, '0', 'Isoprinosine');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_12', '12', 'COVID_TRATAMIENTO_VFC', NULL, '0', 12, '0', 'Melatonina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_13', '13', 'COVID_TRATAMIENTO_VFC', NULL, '0', 13, '0', 'Colchicina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_14', '14', 'COVID_TRATAMIENTO_VFC', NULL, '0', 14, '0', 'Vitamina C');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_15', '15', 'COVID_TRATAMIENTO_VFC', NULL, '0', 15, '0', 'Enoxiparina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_16', '16', 'COVID_TRATAMIENTO_VFC', NULL, '0', 16, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_17', '17', 'COVID_TRATAMIENTO_VFC', NULL, '0', 17, '0', 'Oxigeno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_18', '18', 'COVID_TRATAMIENTO_VFC', NULL, '0', 18, '0', 'Antigripales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_19', '19', 'COVID_TRATAMIENTO_VFC', NULL, '0', 19, '0', 'Prednisona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_20', '20', 'COVID_TRATAMIENTO_VFC', NULL, '0', 20, '0', 'Ibuprofen');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_TRATAMIENTO_VFC_998', '998', 'COVID_TRATAMIENTO_VFC', NULL, '0', 21, '0', 'Otro medicamento');

/*Cuestionario Covid19*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNSNC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/No Sabe/No Contesto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNSNC_01', '1', 'COVID_CAT_SNNSNC', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNSNC_02', '0', 'COVID_CAT_SNNSNC', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNSNC_997', '997', 'COVID_CAT_SNNSNC', NULL, '0', 4, '0', 'No Contesto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SNNSNC_999', '999', 'COVID_CAT_SNNSNC', NULL, '0', 3, '0', 'No sabe/No esta seguro (a)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde buscó ayuda médica para atender enfermedad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA_01', '1', 'COVID_CAT_DONDE_BA', NULL, '0', 1, '0', 'Centro de Salud Sócrates Flores');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA_02', '2', 'COVID_CAT_DONDE_BA', NULL, '0', 2, '0', 'Otro Centro Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA_03', '3', 'COVID_CAT_DONDE_BA', NULL, '0', 3, '0', 'Hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA_04', '4', 'COVID_CAT_DONDE_BA', NULL, '0', 4, '0', 'Médico Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_DONDE_BA_05', '5', 'COVID_CAT_DONDE_BA', NULL, '0', 5, '0', 'No busqué ayuda medica');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIPO_SEG', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para el tipo de seguimiento diario que recibió');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIPO_SEG_01', '1', 'COVID_CAT_TIPO_SEG', NULL, '0', 1, '0', 'Contacto Telefónico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIPO_SEG_02', '2', 'COVID_CAT_TIPO_SEG', NULL, '0', 2, '0', 'Visita en el Domicilio');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo cuánto tiempo después que iniciaron los síntomas buscó ayuda médica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA_01', '1', 'COVID_CAT_TIEMPO_BA', NULL, '0', 1, '0', 'Menos de dos días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA_02', '2', 'COVID_CAT_TIEMPO_BA', NULL, '0', 2, '0', 'Entre 2 a 7 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA_03', '3', 'COVID_CAT_TIEMPO_BA', NULL, '0', 3, '0', 'Mas de 1 semana');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA_997', '997', 'COVID_CAT_TIEMPO_BA', NULL, '0', 5, '0', 'No contestó');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_TIEMPO_BA_999', '999', 'COVID_CAT_TIEMPO_BA', NULL, '0', 4, '0', 'No Sabe/No esta Seguro(a)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD', NULL, NULL, NULL, '1', 0, '0', 'Catalogo serveridad enfermedad Covid19');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_01', '1', 'COVID_CAT_SEVERIDAD', NULL, '0', 1, '0', 'Leve');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_02', '2', 'COVID_CAT_SEVERIDAD', NULL, '0', 2, '0', 'Moderada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_03', '3', 'COVID_CAT_SEVERIDAD', NULL, '0', 3, '0', 'Severa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_04', '4', 'COVID_CAT_SEVERIDAD', NULL, '0', 4, '0', 'Muy severa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_997', '997', 'COVID_CAT_SEVERIDAD', NULL, '0', 6, '0', 'No contestó');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_SEVERIDAD_999', '999', 'COVID_CAT_SEVERIDAD', NULL, '0', 5, '0', 'No Sabe/No esta Seguro(a)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo fumaba antes de que enfermara Covid19 o ahora');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA_01', '1', 'COVID_CAT_FUMA', NULL, '0', 1, '0', 'Todo el día');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA_02', '2', 'COVID_CAT_FUMA', NULL, '0', 2, '0', 'Algunos días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA_03', '3', 'COVID_CAT_FUMA', NULL, '0', 3, '0', 'No Fumaba');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA_997', '997', 'COVID_CAT_FUMA', NULL, '0', 5, '0', 'No contestó');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_FUMA_999', '999', 'COVID_CAT_FUMA', NULL, '0', 4, '0', 'No Sabe/No esta Seguro(a)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_UNIDAD_MED_TR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo unidad de medida tiempo de recuperación enfermedad');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_UNIDAD_MED_TR_01', '1', 'COVID_CAT_UNIDAD_MED_TR', NULL, '0', 1, '0', 'Dias');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_UNIDAD_MED_TR_02', '2', 'COVID_CAT_UNIDAD_MED_TR', NULL, '0', 2, '0', 'Semanas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('COVID_CAT_UNIDAD_MED_TR_03', '3', 'COVID_CAT_UNIDAD_MED_TR', NULL, '0', 3, '0', 'Meses');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CAUSAS_RETIROS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo causas del Retiro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CAUSAS_RETIROS_A', '1', 'CAT_CAUSAS_RETIROS', NULL, '0', 1, '0', 'A - Decisión Padre-Tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CAUSAS_RETIROS_B', '2', 'CAT_CAUSAS_RETIROS', NULL, '0', 2, '0', 'B - Decisión Nuestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CAUSAS_RETIROS_C', '3', 'CAT_CAUSAS_RETIROS', NULL, '0', 3, '0', 'C - Expiración de Participación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CAUSAS_RETIROS_D', '4', 'CAT_CAUSAS_RETIROS', NULL, '0', 4, '0', 'D - Fallecimiento');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_ASENT', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para el tipo de asentimiento que da el participante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_ASENT_01', '1', 'CAT_TIPO_ASENT', NULL, '0', 1, '0', 'Verbal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_ASENT_02', '2', 'CAT_TIPO_ASENT', NULL, '0', 2, '0', 'Escrito');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_ASENT_03', '0', 'CAT_TIPO_ASENT', NULL, '0', 3, '0', 'No da asentimiento');

INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.State', 'Estado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.Place', 'Lugar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.BHC', 'BHC','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lbl.serologia', 'Serologia','0','0',0);