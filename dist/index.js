"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.CameraPlugin = void 0;
class CameraPlugin {
    async takePhoto(options) {
        throw new Error("Native camera implementation not connected yet.");
    }
}
exports.CameraPlugin = CameraPlugin;
