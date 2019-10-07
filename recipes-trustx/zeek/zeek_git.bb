inherit cmake

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=878678038c8739f3591b0acc447383d1"

BRANCH = "master"
SRCREV = "${AUTOREV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PVBASE := "${PV}"
PV = "${PVBASE}+${SRCPV}"

SRC_URI = "git://github.com/zeek/zeek.git"

S = "${WORKDIR}/git/"



DEPENDS = "sed sed-native bison-native cmake-native libpcap bind openssl swig python zeektools-native glibc"

do_configure[depends] += " sed-native:do_build"
do_configure[rdepends] += " sed-native:do_build"


FILES_${PN} += "${base_sbindir}"
INHIBIT_PACKAGE_STRIP = "1"


do_submodule_init () {
	git -C "${S}" submodule update --init --recursive
}

OECMAKE_GENERATOR = "Unix Makefiles"
#OECMAKE_EXTRA_ROOT_PATH := "${STAGING_DIR_NATIVE} /mnt/ramdisk/wruck/ws-zcu104/out-yocto/tmp_container"
OECMAKE_EXTRA_ROOT_PATH := "/mnt/ramdisk/wruck/ws-zcu104/out-yocto/tmp_container ${STAGING_DIR_NATIVE}"




#OECMAKE_TARGET_COMPILE = "bifcl"
#OECMAKE_TARGET_INSTALL = "install"
OECMAKE_C_FLAGS += " -lresolv -lpthread -L${STAGING_DIR_HOST}/usr/lib/"
OECMAKE_CXX_FLAGS += " -lresolv -lpthread -L${STAGING_DIR_HOST}/usr/lib/"
EXTRA_OECMAKE := "-DBINPAC_EXE_PATH=${STAGING_DIR_NATIVE}/usr/bin/binpac -DBINPAC_ROOT_DIR=${STAGING_DIR_NATIVE}/usr/ -DBIFCL_EXE_PATH=${STAGING_DIR_NATIVE}/usr/bin/bifcl -DCMAKE_INSTALL_PREFIX=/usr -DPATH_SUFFIXES=hosttools -DCMAKE_THREAD_PREFER_PTHREAD=ON -DTHREADS_PREFER_PTHREAD_FLAG=ON"
#EXTRA_OECMAKE := "-DCMAKE_THREAD_PREFER_PTHREAD=ON -DTHREADS_PREFER_PTHREAD_FLAG=ON -DPATH_SUFFIXES=hosttools"
# --find-package
# -LH
EXTRA_OECMAKE_BUILD = "VERBOSE=1 -j 1"

#EXTRA_OECMAKE := "-DCMAKE_INSTALL_PREFIX=${STAGING_DIR_HOST} -DZEEK_ROOT_DIR=${STAGING_DIR_HOST} -DPY_MOD_INSTALL_DIR=${STAGING_DIR_HOST} -DZEEK_SCRIPT_INSTALL_PATH=${STAGING_DIR_HOST}/share/zeek -DZEEK_ETC_INSTALL_DIR==${STAGING_DIR_HOST}/etc -DZEEK_LOCAL_STATE_DIR==${STAGING_DIR_HOST}/var -DZEEK_SPOOL_DIR==${STAGING_DIR_HOST}/spool -DZEEK_LOG_DIR==${STAGING_DIR_HOST}/var/log"

addtask do_submodule_init after do_unpack before do_patch
