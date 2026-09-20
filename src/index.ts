export interface CameraOptions {
  quality?: number;
  camera?: "front" | "back";
}

export interface CameraResult {
  base64: string;
  mimeType: string;
  fileName: string;
}

export class CameraPlugin {

  async takePhoto(options?: CameraOptions): Promise<CameraResult> {
    throw new Error("Native camera implementation not connected yet.");
  }

}