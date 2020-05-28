INSERT INTO sellers
    VALUES
        (1, 'Hugo'),
        (2, 'Alma'),
        (3, 'Paco');

INSERT INTO modules
    VALUES
        (1, 'Post-Venta'),
        (2, 'venta');

INSERT INTO sellers_modules
    VALUES
        (1,1),
        (2,1),
        (2,2),
        (3,2);

INSERT INTO processes
    VALUES
        (1, 'Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Contacto', 2),
        (2, 'Mensaje del proceso Cita Servicio, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Cita Servicio', 1),
        (3, 'Mensaje del proceso Pintura, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Pintura', 1),
        (4, 'Mensaje del proceso Refacciones, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Refacciones', 1),
        (5, 'Mensaje del proceso Cotizaciones, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotización de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Contizaciones', 2);

INSERT INTO mailing_lists
    VALUES
        (1, 'Confimracián al cliente'),
        (2, 'Envío a agente de servicio'),
        (3, 'Confimracion cita a cliente');

INSERT INTO processes_mailing_lists
    VALUES
        (1, 1),
        (1, 2),
        (2, 2),
        (3, 3),
        (4, 1),
        (5, 2);

INSERT INTO addressees
    VALUES
        (1, 'ericksuarezdev@gmail.com', 'Sofware', 'Erick Suarez', 1),
        (2, 'ericksuarezdev@gmail.com', 'Lista 2', 'Erick Lista 2', 2),
        (3, 'ericksuarezdev@gmail.com', 'Lista 3', 'Erick Lista 3', 2);
