package actions;

import gl.GLCamera;
import gl.HasPosition;
import gl.scenegraph.MeshComponent;
import util.Vec;
import util.Wrapper;
import worldData.MoveObjComp;
import worldData.Obj;
import android.R.integer;
import android.view.MotionEvent;

public class ActionMoveObject extends ActionDoAlongAxis {

	private Wrapper target;

	/**
	 * @param wrapper
	 * @param camera
	 * @param trackballFactor
	 *            should be around 2-15
	 * @param touchscreenFactor
	 *            25 would be good value to start.The higher the value the
	 *            slower the movement
	 */
	public ActionMoveObject(Wrapper wrapper, GLCamera camera,
			float trackballFactor, float touchscreenFactor) {
		super(camera, trackballFactor, touchscreenFactor);
		target = wrapper;
	}

	@Override
	public void doAlongViewAxis(float x, float y) {
		if (target != null && target.getObject() instanceof Obj)
			foundObj((Obj) target.getObject(), x, y);
	}

	private void foundObj(Obj obj, float x, float y) {
		MoveObjComp mc = obj.getComp(MoveObjComp.class);
		Vec pos = obj.getPosition();
		if (mc != null) {
			if (mc.myTargetPos == null && pos != null) {
				mc.myTargetPos = pos.copy();
			}
			mc.myTargetPos.add(x, y, 0.0f);
		} else if (pos != null) {
			/*
			 * if no move comp was found in the target object, the mesh itself
			 * will be used
			 */
			pos.add(x, y, 0);
		}
	}
}
