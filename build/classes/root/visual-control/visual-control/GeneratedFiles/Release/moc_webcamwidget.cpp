/****************************************************************************
** Meta object code from reading C++ file 'webcamwidget.h'
**
** Created: Wed 2. Feb 00:21:41 2011
**      by: The Qt Meta Object Compiler version 62 (Qt 4.7.1)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../gui/webcamwidget.h"
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'webcamwidget.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 62
#error "This file was generated using the moc from 4.7.1. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
static const uint qt_meta_data_gui__WebcamWidget[] = {

 // content:
       5,       // revision
       0,       // classname
       0,    0, // classinfo
       0,    0, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

       0        // eod
};

static const char qt_meta_stringdata_gui__WebcamWidget[] = {
    "gui::WebcamWidget\0"
};

const QMetaObject gui::WebcamWidget::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_gui__WebcamWidget,
      qt_meta_data_gui__WebcamWidget, 0 }
};

#ifdef Q_NO_DATA_RELOCATION
const QMetaObject &gui::WebcamWidget::getStaticMetaObject() { return staticMetaObject; }
#endif //Q_NO_DATA_RELOCATION

const QMetaObject *gui::WebcamWidget::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->metaObject : &staticMetaObject;
}

void *gui::WebcamWidget::qt_metacast(const char *_clname)
{
    if (!_clname) return 0;
    if (!strcmp(_clname, qt_meta_stringdata_gui__WebcamWidget))
        return static_cast<void*>(const_cast< WebcamWidget*>(this));
    if (!strcmp(_clname, "cognition::FrameReceiver"))
        return static_cast< cognition::FrameReceiver*>(const_cast< WebcamWidget*>(this));
    if (!strcmp(_clname, "cognition::Controller"))
        return static_cast< cognition::Controller*>(const_cast< WebcamWidget*>(this));
    return QWidget::qt_metacast(_clname);
}

int gui::WebcamWidget::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    return _id;
}
QT_END_MOC_NAMESPACE
