/****************************************************************************
 ****************************************************************************
 ***
 ***   This header was automatically generated from a Linux kernel header
 ***   of the same name, to make information necessary for userspace to
 ***   call into the kernel available to libc.  It contains only constants,
 ***   structures, and macros generated from the original header, and thus,
 ***   contains no copyrightable information.
 ***
 ***   To edit the content of this header, modify the corresponding
 ***   source file (e.g. under external/kernel-headers/original/) then
 ***   run bionic/libc/kernel/tools/update_all.py
 ***
 ***   Any manual change here will be lost the next time this script will
 ***   be run. You've been warned!
 ***
 ****************************************************************************
 ****************************************************************************/
#ifndef _XT_HARDIDLETIMER_H
#define _XT_HARDIDLETIMER_H
#include <linux/types.h>
#define MAX_HARDIDLETIMER_LABEL_SIZE 28
#define NLMSG_MAX_SIZE 64
#define NL_EVENT_TYPE_INACTIVE 0
#define NL_EVENT_TYPE_ACTIVE 1
struct hardidletimer_tg_info {
  __u32 timeout;
  char label[MAX_HARDIDLETIMER_LABEL_SIZE];
  __u8 send_nl_msg;
  struct hardidletimer_tg * timer __attribute__((aligned(8)));
};
#endif
