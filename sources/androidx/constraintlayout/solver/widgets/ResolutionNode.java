package androidx.constraintlayout.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode {
    HashSet<ResolutionNode> dependents = new HashSet<>(2);
    int state = 0;

    public void resolve() {
    }

    public void addDependent(ResolutionNode resolutionNode) {
        this.dependents.add(resolutionNode);
    }

    public void reset() {
        this.state = 0;
        this.dependents.clear();
    }

    public void invalidate() {
        this.state = 0;
        Iterator<ResolutionNode> it = this.dependents.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    public void didResolve() {
        this.state = 1;
        Iterator<ResolutionNode> it = this.dependents.iterator();
        while (it.hasNext()) {
            it.next().resolve();
        }
    }

    public boolean isResolved() {
        return this.state == 1;
    }
}
