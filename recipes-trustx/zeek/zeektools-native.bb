LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=878678038c8739f3591b0acc447383d1"

BRANCH = "master"
SRCREV = "${AUTOREV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit cmake native

PVBASE := "${PV}"
PV = "${PVBASE}+${SRCPV}"

SRC_URI = "git://github.com/zeek/zeek.git"

S = "${WORKDIR}/git/"


DEPENDS = "sed-native bison-native cmake-native libpcap-native bind-native openssl-native swig-native python-native"


do_submodule_init () {
	git -C "${S}" submodule update --init --recursive
}

OECMAKE_GENERATOR = "Unix Makefiles"
#OECMAKE_EXTRA_ROOT_PATH := "${TMPDIR} ${S}/aux/bifcl/build/src/bifcl ${S}/aux/binpac/build/src/binpac"
OECMAKE_TARGET_COMPILE = "binpac"
OECMAKE_TARGET_INSTALL = "install"
OECMAKE_C_FLAGS += " -lresolv"
OECMAKE_CXX_FLAGS += " -lresolv"

#EXTRA_OECMAKE := "-DCMAKE_INSTALL_PREFIX=${STAGING_DIR_HOST} -DZEEK_ROOT_DIR=${STAGING_DIR_HOST} -DPY_MOD_INSTALL_DIR=${STAGING_DIR_HOST} -DZEEK_SCRIPT_INSTALL_PATH=${STAGING_DIR_HOST}/share/zeek -DZEEK_ETC_INSTALL_DIR==${STAGING_DIR_HOST}/etc -DZEEK_LOCAL_STATE_DIR==${STAGING_DIR_HOST}/var -DZEEK_SPOOL_DIR==${STAGING_DIR_HOST}/spool -DZEEK_LOG_DIR==${STAGING_DIR_HOST}/var/log"

#EXTRA_OECMAKE := "-DCMAKE_INSTALL_PREFIX=/usr -DZEEK_ROOT_DIR=/usr -DPY_MOD_INSTALL_DIR=/lib/zeekctl"
EXTRA_OECMAKE := "-DBUILD_STATIC_BINPAC:BOOL=ON"

addtask do_submodule_init after do_unpack before do_patch

do_install_append () {
	bbwarn " rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro"
	rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro)"
	ln -s './zeek-wrapper' ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro)"

	bbwarn " rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config"
	rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config)"
	ln -s './zeek-wrapper' ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config)"
}

do_deploy_append () {
	bbwarn " rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro"
	rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro)"
	ln -s './zeek-wrapper' ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro)"

	bbwarn " rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config"
	rm ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config)"
	ln -s './zeek-wrapper' ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config
	bbwarn "ls: $(ls -l ${D}/${STAGING_DIR_NATIVE}/usr/bin/bro-config)"
}
