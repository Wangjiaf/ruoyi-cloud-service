-- ruoyi_cloud.chat_group definition

CREATE TABLE `chat_group` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `group_id` bigint NOT NULL COMMENT '群号',
  `group_name` varchar(255) DEFAULT NULL COMMENT '群名称',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群组主数据';


-- ruoyi_cloud.chat_group_message definition

CREATE TABLE `chat_group_message` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `group_id` bigint NOT NULL COMMENT '群号',
  `from_user_id` bigint NOT NULL COMMENT '发送者用户ID',
  `message_content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `revoke_flag` char(1) DEFAULT '0' COMMENT '撤回标识(0代表未撤回 1代表撤回)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群消息';


-- ruoyi_cloud.chat_group_user definition

CREATE TABLE `chat_group_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `group_id` bigint NOT NULL COMMENT '群号',
  `user_id` bigint NOT NULL COMMENT '群成员用户ID',
  `group_user_role` varchar(50) DEFAULT '0' COMMENT '成员在群内角色',
  `leave_flag` char(1) DEFAULT '0' COMMENT '成员离群标识(0代表未离群 1代表离群)',
  `leave_time` datetime DEFAULT NULL COMMENT '成员离群时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群成员数据';


-- ruoyi_cloud.chat_tip definition

CREATE TABLE `chat_tip` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `chat_type` varchar(50) NOT NULL COMMENT '聊天方式',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `tip_from_id` bigint NOT NULL COMMENT '好友ID/群ID',
  `tip_content` varchar(200) DEFAULT NULL COMMENT '提示内容',
  `un_read_count` bigint DEFAULT '0' COMMENT '未读消息数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息提示表';


-- ruoyi_cloud.chat_user_message definition

CREATE TABLE `chat_user_message` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `from_user_id` bigint NOT NULL COMMENT '发送者用户ID',
  `to_user_id` bigint NOT NULL COMMENT '接收者用户ID',
  `message_content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `revoke_flag` char(1) DEFAULT '0' COMMENT '撤回标识(0代表存在 1代表撤回)',
  `from_user_del_flag` char(1) DEFAULT '0' COMMENT '发送者删除标识(0代表存在 1代表删除)',
  `to_user_del_flag` char(1) DEFAULT '0' COMMENT '接收者删除标识(0代表存在 1代表删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友聊天消息表';


-- ruoyi_cloud.chat_user_relation definition

CREATE TABLE `chat_user_relation` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识(0代表存在 1代表删除)',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `relation_user_id` bigint NOT NULL COMMENT '好友用户ID',
  `relation_user_remark` varchar(200) DEFAULT NULL COMMENT '好友备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好友关系表';