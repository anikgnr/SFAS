INSERT INTO `cy_be_users` ( `id` , `created` , `created_by` , `last_modified` , `last_modified_by` , `is_active` , `first_name` , `last_name` , `mobile_number` , `password` , `role` , `user_name` )
VALUES (
NULL , '2013-02-12 20:01:35', 'super-admin', '2013-02-12 20:01:35', 'super-admin', '1', 'Site', 'Admin', NULL , 'admin', 'ROLE_ADMIN', 'admin'
);

INSERT INTO  `sfasdb`.`cy_be_regions` (`id` ,`region_name`)
VALUES ('1',  'Dhaka Metro'), ('2',  'Chittagong Metro');
