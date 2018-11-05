
INSERT INTO `t_attachmentmeta` VALUES ('meta1', '未标题-1 - 副本 (2).png');
INSERT INTO `t_attachmentmeta` VALUES ('meta2', '未标题-1.png');
INSERT INTO `t_attachmentmeta` VALUES ('meta3', '未标题-1 - 副本 (4).png');
INSERT INTO `t_attachmentmeta` VALUES ('meta4', '未标题-1 - 副本.png');
INSERT INTO `t_attachmentmeta` VALUES ('meta5', '未标题-1 - 副本 (3).png');

INSERT INTO `t_attachmentcopy` VALUES ('copy1', '2016-12-27', 'com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment', 'meta4');
INSERT INTO `t_attachmentcopy` VALUES ('copy2', '2016-12-27', 'com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment', 'meta2');
INSERT INTO `t_attachmentcopy` VALUES ('copy3', '2016-12-27', 'com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment', 'meta5');
INSERT INTO `t_attachmentcopy` VALUES ('copy4', '2016-12-27', 'com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment', 'meta1');
INSERT INTO `t_attachmentcopy` VALUES ('copy5', '2016-12-27', 'com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment', 'meta3');

-- ----------------------------
-- INSERT INTO `t_dbattachmentimpl` VALUES ('copy1', 0x89504E470D0A1A0A0000000D49484452000000010000000108060000001F15C489000000017352474200AECE1CE90000000467414D410000B18F0BFC61050000000970485973000012740000127401DE661F780000000D49444154185763F8FFFFFF7F0009FB03FD054345CA0000000049454E44AE426082);
-- INSERT INTO `t_dbattachmentimpl` VALUES ('copy2', 0x89504E470D0A1A0A0000000D49484452000000010000000108060000001F15C489000000017352474200AECE1CE90000000467414D410000B18F0BFC61050000000970485973000012740000127401DE661F780000000D49444154185763F8FFFFFF7F0009FB03FD054345CA0000000049454E44AE426082);
-- INSERT INTO `t_dbattachmentimpl` VALUES ('copy3', 0x89504E470D0A1A0A0000000D49484452000000010000000108060000001F15C489000000017352474200AECE1CE90000000467414D410000B18F0BFC61050000000970485973000012740000127401DE661F780000000D49444154185763F8FFFFFF7F0009FB03FD054345CA0000000049454E44AE426082);
-- INSERT INTO `t_dbattachmentimpl` VALUES ('copy4', 0x89504E470D0A1A0A0000000D49484452000000010000000108060000001F15C489000000017352474200AECE1CE90000000467414D410000B18F0BFC61050000000970485973000012740000127401DE661F780000000D49444154185763F8FFFFFF7F0009FB03FD054345CA0000000049454E44AE426082);
-- INSERT INTO `t_dbattachmentimpl` VALUES ('copy5', 0x89504E470D0A1A0A0000000D49484452000000010000000108060000001F15C489000000017352474200AECE1CE90000000467414D410000B18F0BFC61050000000970485973000012740000127401DE661F780000000D49444154185763F8FFFFFF7F0009FB03FD054345CA0000000049454E44AE426082);
-- ----------------------------

INSERT INTO `t_gwry_rybk` (`id`, `sfzh`, `bh`, `czzt`, `xm`, `sjzt`, `bkkssj`, `bkjssj`) VALUES ('人员布控赵', '340403', '人员布控赵bm', '2', '赵', '1', '2016-12-26 00:00:00', '2222-12-26 00:00:00') ;
INSERT INTO `t_gwry_rybk_bkzp` (`id`, `fjy_id`, `rybk_id`) VALUES ('人员布控赵bkzp1', 'meta1', '人员布控赵') ;
INSERT INTO `t_gwry_rybk_bkzp` (`id`, `fjy_id`, `rybk_id`) VALUES ('人员布控赵bkzp2', 'meta2', '人员布控赵') ;
INSERT INTO `t_gwry_rybkyddxx` (`id`, `mac`, `sjhm`, `rybk_id`) VALUES ('人员布控赵rybkyddxx1', '人员布控赵mac1', '2222', '人员布控赵') ;
INSERT INTO `t_gwry_rybkyddxx` (`id`, `mac`, `sjhm`, `rybk_id`) VALUES ('人员布控赵rybkyddxx2', '人员布控赵mac2', '3333', '人员布控赵') ;

INSERT INTO `t_gwry_rybk` (`id`, `sfzh`, `bh`, `czzt`, `xm`, `sjzt`, `bkkssj`, `bkjssj`) VALUES ('人员布控钱', '340403222', '人员布控钱bm', '2', '钱', '1', '2016-12-26 00:00:00', '2222-12-26 00:00:00') ;
INSERT INTO `t_gwry_rybk_bkzp` (`id`, `fjy_id`, `rybk_id`) VALUES ('人员布控钱bkzp1', 'meta3', '人员布控钱') ;
INSERT INTO `t_gwry_rybk_bkzp` (`id`, `fjy_id`, `rybk_id`) VALUES ('人员布控钱bkzp2', 'meta4', '人员布控钱') ;
INSERT INTO `t_gwry_rybkyddxx` (`id`, `mac`, `sjhm`, `rybk_id`) VALUES ('人员布控钱rybkyddxx1', '人员布控钱mac1', '66666', '人员布控钱') ;
INSERT INTO `t_gwry_rybkyddxx` (`id`, `mac`, `sjhm`, `rybk_id`) VALUES ('人员布控钱rybkyddxx2', '人员布控钱mac2', '33888888833', '人员布控钱') ;