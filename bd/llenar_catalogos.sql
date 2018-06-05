use silalibrodb;

insert into pais(pais_nombre) values ('México'),('Colombia'),('Estados Unidos');

INSERT INTO `silalibrodb`.`autor`
(`autor_nombre`,
`autor_apellidoPaterno`,
`autor_apellidoMaterno`,
`autor_idpais`)
VALUES
('Ray',
'Douglas',
'Bradbury',
3);

INSERT INTO `silalibrodb`.`libro`
(`libro_sku`,
`libro_titulo`,
`libro_idautor`,
`librocol`)
VALUES
('SKULARGOYREAL',
'Fahrenheit 451',
1,
'resources/portadas/Fahrenheit451.jpg');
