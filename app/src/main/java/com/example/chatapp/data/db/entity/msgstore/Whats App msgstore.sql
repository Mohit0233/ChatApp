create table messages (
  _id integer primary key autoincrement,
  key_remote_jid text not null,
  key_from_me integer,
  key_id text not null,
  status integer,
  needs_push integer,
  data text,
  timestamp integer,
  media_url text,
  media_mime_type text,
  media_wa_type text,
  media_size integer,
  media_name text,
  media_caption text,
  media_hash text,
  media_duration integer,
  origin integer,
  latitude real,
  longitude real,
  thumb_image text,
  remote_resource text,
  received_timestamp integer,
  send_timestamp integer,
  receipt_server_timestamp integer,
  receipt_device_timestamp integer,
  read_device_timestamp integer,
  played_device_timestamp integer,
  raw_data blob,
  recipient_count integer,
  participant_hash text,
  starred integer,
  quoted_row_id integer,
  mentioned_jids text,
  multicast_id text,
  edit_version integer,
  media_enc_hash text,
  payment_transaction_id text,
  forwarded text
);
create table sqlite_sequence(name, seq);
create unique index messages_key_index on messages (
  key_remote_jid, 
  key_from_me, 
  key_id
);
create index messages_jid_id_index on messages (
    key_remote_jid, 
    _id
);
create index media_hash_index on messages (
    media_hash
);
create index media_type_index on messages (
    media_wa_type
);
create index media_type_jid_index on messages (key_remote_jid, media_wa_type);
create index starred_index on messages (starred);
create table chat_list (
  _id integer primary key autoincrement,
  key_remote_jid text unique,
  message_table_id integer,
  subject text,
  creation integer,
  last_read_message_table_id integer,
  last_read_receipt_sent_message_table_id integer,
  archived integer,
  sort_timestamp integer,
  mod_tag integer,
  gen real,
  my_messages integer,
  plaintext_disabled boolean,
  last_message_table_id integer,
  unseen_message_count integer,
  unseen_missed_calls_count integer,
  unseen_row_count integer,
  vcard_ui_dismissed integer,
  deleted_message_id integer,
  deleted_starred_message_id integer,
  deleted_message_categories text,
  change_number_notified_message_id integer,
  last_important_message_table_id integer,
  show_group_description integer
);
create table props (
  _id integer primary key autoincrement,
  key text unique,
  value text
);
create virtual table messages_fts using FTS3();
create table if not exists 'messages_fts_content'(
  docid integer primary key,
  'c0content'
);
create table if not exists 'messages_fts_segments'(
  blockid integer primary key,
  block blob
);
create table if not exists 'messages_fts_segdir'(
  level integer,
  idx integer,
  start_block integer,
  leaves_end_block integer,
  end_block integer,
  root blob,
  primary key(level, idx)
);
create trigger messages_bd_trigger before delete on messages begin
delete from
  messages_fts 
where
  docid = old._id;
end;
create table messages_quotes (
  _id integer primary key autoincrement,
  key_remote_jid text not null,
  key_from_me integer,
  key_id text not null,
  status integer,
  needs_push integer,
  data text,
  timestamp integer,
  media_url text,
  media_mime_type text,
  media_wa_type text,
  media_size integer,
  media_name text,
  media_caption text,
  media_hash text,
  media_duration integer,
  origin integer,
  latitude real,
  longitude real,
  thumb_image text,
  remote_resource text,
  received_timestamp integer,
  send_timestamp integer,
  receipt_server_timestamp integer,
  receipt_device_timestamp integer,
  read_device_timestamp integer,
  played_device_timestamp integer,
  raw_data blob,
  recipient_count integer,
  participant_hash text,
  starred integer,
  quoted_row_id integer,
  mentioned_jids text,
  multicast_id text,
  edit_version integer,
  media_enc_hash text,
  payment_transaction_id text
);
create trigger messages_bd_for_quotes_trigger before delete on messages begin
delete from
  messages_quotes 
where
  _id = old.quoted_row_id;
end;
create table messages_vcards (
  _id integer primary key autoincrement,
  message_row_id integer,
  sender_jid text,
  vcard text,
  chat_jid text
);
create table messages_vcards_jids (
  _id integer primary key autoincrement,
  message_row_id integer,
  vcard_jid text,
  vcard_row_id integer
);
create trigger messages_bd_for_vcards_trigger before delete on messages begin
delete from
  messages_vcards 
where
  message_row_id = old._id;
end;
create trigger messages_bd_for_vcards_jids_trigger before delete on messages begin
delete from
  messages_vcards_jids 
where
  message_row_id = old._id;
end;
create table messages_edits (
  _id integer primary key autoincrement,
  key_remote_jid text not null,
  key_from_me integer,
  key_id text not null,
  status integer,
  needs_push integer,
  data text,
  timestamp integer,
  media_url text,
  media_mime_type text,
  media_wa_type text,
  media_size integer,
  media_name text,
  media_caption text,
  media_hash text,
  media_duration integer,
  origin integer,
  latitude real,
  longitude real,
  thumb_image text,
  remote_resource text,
  received_timestamp integer,
  send_timestamp integer,
  receipt_server_timestamp integer,
  receipt_device_timestamp integer,
  read_device_timestamp integer,
  played_device_timestamp integer,
  raw_data blob,
  recipient_count integer,
  participant_hash text,
  starred integer,
  quoted_row_id integer,
  mentioned_jids text,
  multicast_id text,
  edit_version integer,
  media_enc_hash text,
  payment_transaction_id text
);
create table messages_links (
  _id integer primary key autoincrement,
  key_remote_jid text,
  message_row_id integer,
  link_index integer
);
create trigger messages_bd_for_links_trigger before delete on messages begin
delete from
  messages_links 
where
  message_row_id = old._id;
end;
create table frequents (
  _id integer primary key autoincrement,
  jid text not null,
  type integer not null,
  message_count integer not null
);
create table receipts (
  _id integer primary key autoincrement,
  key_remote_jid text not null,
  key_id text not null,
  remote_resource text,
  receipt_device_timestamp integer,
  read_device_timestamp integer,
  played_device_timestamp integer
);
create index receipts_key_index on receipts (key_remote_jid, key_id);

create trigger messages_bd_for_receipts_trigger before delete on messages begin
delete from
  receipts 
where
  key_remote_jid = old.key_remote_jid 
  and key_id = old.key_id;
end;

create table group_participants (
  _id integer primary key autoincrement,
  gjid text not null,
  jid text not null,
  admin integer,
  pending integer,
  sent_sender_key integer
);

create unique index group_participants_index on group_participants (gjid, jid);

create table group_participants_history (
  _id integer primary key autoincrement,
  timestamp datetime not null, gjid text not null,
  jid text not null, action integer not null,
  old_phash text not null, new_phash text not null
);

create index group_participants_history_index on group_participants_history (gjid);

create table media_refs (
  _id integer primary key autoincrement,
  path text unique, ref_count integer
);

create table media_streaming_sidecar (
  _id integer primary key autoincrement,
  sidecar blob, timestamp datetime,
  key_remote_jid text not null, key_from_me integer,
  key_id text not null
);

create table message_thumbnails (
  thumbnail blob, timestamp datetime,
  key_remote_jid text not null, key_from_me integer,
  key_id text not null
);

create unique index messages_thumbnail_key_index on message_thumbnails (
  key_remote_jid, key_from_me, key_id
);

create table status_list (
  _id integer primary key autoincrement,
  key_remote_jid text unique, message_table_id integer,
  last_read_message_table_id integer,
  last_read_receipt_sent_message_table_id integer,
  first_unread_message_table_id integer,
  autodownload_limit_message_table_id integer,
  timestamp integer, unseen_count integer,
  total_count integer
);

create table conversion_tuples (
  key_remote_jid text primary key,
  data text,
  source text,
  last_interaction integer,
  first_interaction integer
);

create table labels (
  _id integer primary key autoincrement,
  label_name text,
  predefined_id integer
);

create table labeled_messages (
  _id integer primary key autoincrement,
  label_id integer not null,
  message_row_id integer not null
);

create unique index labeled_messages_index on labeled_messages (label_id, message_row_id);

create trigger messages_bd_for_labeled_messages_trigger before delete on messages begin
delete from
  labeled_messages 
where
  message_row_id = old._id;
end;

create trigger labels_bd_for_labeled_messages_trigger before delete on labels begin
delete from
  labeled_messages 
where
  label_id = old._id;
end;

create table labeled_jids (
  _id integer primary key autoincrement,
  label_id integer not null,
  jid text
);

create unique index labeled_jids_index on labeled_jids (label_id, jid);

create trigger labels_bd_for_labeled_jids_trigger before delete on labels begin
delete from
  labeled_jids 
where
  label_id = old._id;
end;
create table deleted_chat_jobs (
  _id integer primary key autoincrement,
  key_remote_jid text not null,
  block_size integer,
  deleted_message_id integer,
  deleted_starred_message_id integer,
  deleted_message_categories text,
  delete_files boolean
);

create index deleted_chat_jobs_index on deleted_chat_jobs (
    key_remote_jid, 
    _id);

create unique index labels_index on labels (
    label_name
);

create table pay_transactions (
  key_remote_jid text,
  key_from_me integer,
  key_id text,
  id text, timestamp integer,
  status integer,
  error_code text,
  sender text,
  receiver text,
  type integer,
  currency text,
  amount_1000, 
  credential_id text,
  methods text,
  bank_transaction_id text,
  metadata text,
  init_timestamp integer
);

create unique index message_payment_transactions_index on pay_transactions (key_id);

create unique index message_payment_transactions_id_index on pay_transactions (id);

create table messages_dehydrated_hsm (
  _id integer primary key autoincrement,
  message_row_id integer unique,
  message_elementname text,
  message_namespace text,
  message_lg text
);

create trigger messages_bd_for_dehydrated_hsms_trigger before delete on messages begin
delete from
  messages_dehydrated_hsm 
where
  message_row_id = old._id;
end;
